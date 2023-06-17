package utils

import CommandResult
import data.Coordinates
import data.FuelType
import data.Vehicle
import data.VehicleType
import java.sql.Timestamp
import java.time.LocalDate

class StorageManager: Storage<LinkedHashMap<Int, Vehicle>, Int, Vehicle> {
    private val date: LocalDate = LocalDate.now()
    private val dataBaseManager = DataBaseManager()


    val vehicleCollection = LinkedHashMap<Int, Vehicle>()





    override fun clear() {
        vehicleCollection.clear()
    }

    override fun removeKey(id: Int) {
        vehicleCollection.remove(id)
    }

    override fun getInfo(): String {
        return "Коллекция  ${this.javaClass} \n" +
                "тип: LinkedHashMap количество элементов  ${vehicleCollection.size} \n" +
                "дата инициализации $date"
    }

    override fun insert(id: Int, element: Vehicle) {
        vehicleCollection[id] = element
    }


    override fun getCollection(predicate: Map.Entry<Int, Vehicle>.() -> Boolean): LinkedHashMap<Int, Vehicle> =
        LinkedHashMap(vehicleCollection.filter(predicate))

    override fun update(id: Int, element: Vehicle) {

    }

    private fun loadFromDatabase() {
        val connection = dataBaseManager.connection
        val statement = connection?.createStatement()
        val resultSet = statement?.executeQuery("SELECT * FROM Vehicles")

        while (resultSet?.next() == true) {
            val vehicle = Vehicle(
                id = resultSet.getInt("id"),
                name = resultSet.getString("name"),
                coordinates = Coordinates(
                    x = resultSet.getFloat("coordinate_x"),
                    y = resultSet.getDouble("coordinate_y")
                ),
                creationDate = resultSet.getTimestamp("creation_date").toLocalDateTime(),
                enginePower = resultSet.getInt("engine_power"),
                distanceTravelled = resultSet.getLong("distance_travelled"),
                vehicleType = resultSet.getString("vehicle_type")?.let { VehicleType.valueOf(it) },
                fuelType = resultSet.getString("fuel_type")?.let { FuelType.valueOf(it) },
            )

            vehicleCollection.putIfAbsent(1,vehicle)
        }

    }

    fun add(vehicle: Vehicle): CommandResult {

        val connection = dataBaseManager.connection
        val preparedStatement = connection?.prepareStatement(
            """
    INSERT INTO Vehicles(
        id, 
        name, 
        coordinate_x, 
        coordinate_y, 
        creation_date, 
        engine_power, 
        distance_travelled, 
        vehicle_type, 
        fuel_type
    )
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
"""
        )

        preparedStatement?.setInt(1, vehicle.id)
        preparedStatement?.setString(2, vehicle.name)
        preparedStatement?.setFloat(3, vehicle.coordinates.x)
        preparedStatement?.setDouble(4, vehicle.coordinates.y)
        preparedStatement?.setTimestamp(5, Timestamp.valueOf(vehicle.creationDate))
        preparedStatement?.setInt(6, vehicle.enginePower)
        vehicle.distanceTravelled?.let { preparedStatement?.setLong(7, it) }
        preparedStatement?.setString(8, vehicle.vehicleType?.name)
        preparedStatement?.setString(9, vehicle.fuelType?.name)

        val rowsAffected = preparedStatement?.executeUpdate() ?: 0

        if (rowsAffected > 0) {
            vehicleCollection.putIfAbsent(1, vehicle)  // LabWork object already includes owner
            return CommandResult.Success("add")
        } else {
            throw IllegalStateException("Failed to add vehicle collection to the database")
        }

    }

    fun removeByKey(id: Int): Boolean {
        val vehicle = vehicleCollection[id]
        if (vehicle != null) {
            val iterator = vehicleCollection.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                if (entry.key == id) {
                    iterator.remove()
                    return true
                }
            }
        }
        throw IllegalArgumentException("Cannot remove a lab work that you did not create")
    }

}
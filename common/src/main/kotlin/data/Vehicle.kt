package data

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * Класс, объекты которого мы храним в коллекции
 */
@Serializable
data class Vehicle(
    val name: String, //Поле не может быть null, Строка не может быть пустой
    val coordinates: Coordinates,//Поле не может быть null
    val enginePower: Int,//Значение поля должно быть больше 0
    val distanceTravelled: Long?,//Значение поля должно быть больше 0
    val vehicleType: VehicleType?,//Поле может быть null
    val fuelType: FuelType?,//Поле может быть null
    val id: Int = generateId(arrayOf(name,VehicleType.toString())),
    @Contextual
    val creationDate: LocalDate = LocalDate.now()
) : Comparable<Vehicle> {


    /**
     * Compares this Vehicle object with another Vehicle object by their IDs.
     *
     * @param other The Vehicle object to compare with.
     * @return A negative value if this object is less than the other object, 0 if they are equal, and a positive value if this object is greater than the other object.
     */
    override fun compareTo(other: Vehicle): Int {
        return id.compareTo(other.id)
    }
}

fun generateId(array: Array<String>): Int {
    return array.contentHashCode()
}
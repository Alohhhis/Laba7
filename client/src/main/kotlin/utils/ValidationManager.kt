package utils

import data.*

class ValidationManager(
    private val interactor: Interactor,
    private val userManager: ReaderWriter
) : Validator {

    override fun getInt(): Int {
        var res: Int? = null
        while (res == null) {
            userManager.write("Вы должны ввести аргумент типа число: ")
            res = userManager.readLine().toIntOrNull()
        }
        return res
    }

    override fun getFuelType(): FuelType {
        var res: FuelType? = null
        while (res == null) {
            userManager.write("Вы должны ввести тип топлива (ELECTRICITY,\n" +
                    "    NUCLEAR,\n" +
                    "    PLASMA): ")
            res = FuelType.valueOfOrNull(userManager.readLine().uppercase())
        }
        return res
    }

    override fun getString(): String {
        userManager.write("Вы должны ввести аргумент типа строка: ")
        return userManager.readLine()
    }

    override fun getVehicleType(): VehicleType {
        var res: VehicleType? = null
        while (res == null) {
            userManager.write("Вы должны ввести тип транспорта (CAR,\n" +
                    "    SUBMARINE,\n" +
                    "    SHIP,\n" +
                    "    BICYCLE,\n" +
                    "    HOVERBOARD): ")
            res = VehicleType.valueOfOrNull(userManager.readLine().uppercase())
        }
        return res
    }


    override fun getVehicle(): Vehicle {
        val name = userManager.getValidatedValue("Введите название транспорта: ") {
            it.isNotEmpty()
        }
        val coordX = userManager.getValidatedValue("Введите координату X: ") {
            it.toFloatOrNull()!= null
        }.toDouble()
        val coordY = userManager.getValidatedValue("Введите координату Y (<=297): ") {
            (it.toDoubleOrNull()?: return@getValidatedValue false) <= 297
        }.toFloat()
        val enginePower = userManager.getValidatedValue("Введите мощность двигателя: ") {
            (it.toIntOrNull() ?: return@getValidatedValue false) > 0
        }.toInt()
        val distanceTravelled = userManager.getValidatedValue("Введите пробег: ") {
            (it.toIntOrNull() ?: return@getValidatedValue false) > 0
        }.toInt()
        val fuelType =
            userManager.getValidatedValue("Введите тип топлива (ELECTRICITY,\n" +
                    "    NUCLEAR,\n" +
                    "    PLASMA): ") {
                FuelType.valueOfOrNull(it.uppercase()) != null
            }
        val vehicleType =
            userManager.getValidatedValue("Введите тип транспорта (CAR,\n" +
                    "    SUBMARINE,\n" +
                    "    SHIP,\n" +
                    "    BICYCLE,\n" +
                    "    HOVERBOARD): ") {
                VehicleType.valueOfOrNull(it.uppercase()) != null
            }


        return Vehicle(
            name = name,
            coordinates = Coordinates(coordX.toFloat(), coordY.toDouble()),
            enginePower = enginePower,
            distanceTravelled = distanceTravelled.toLong(),
            fuelType = FuelType.valueOf(fuelType.uppercase()),
            vehicleType = VehicleType.valueOf(vehicleType.uppercase())
        )

    }
}
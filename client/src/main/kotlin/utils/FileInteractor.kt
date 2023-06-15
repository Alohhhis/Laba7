package utils

import ArgumentType
import ClientMain
import data.*
import exceptions.CommandFileException
import org.koin.core.component.KoinComponent

/**
 * Выполняет команды из файла
 *
 * @param interactor делегирует этому большинство методов
 * @param lines имеют все строки из командного файла
 * @throws [CommandFileException], если произошло исключение
 */
class FileInteractor(
    private val interactor: Interactor,
    private val lines: List<String>
) : KoinComponent, Interactor by interactor, Validator {
    private var index = 0
    private var lastArgument: String? = null
    private lateinit var clientMain: ClientMain

    /**
     * Начинает выполнять команды из файла
     *
     * @param clientApp текущий клиент, подключенный к серверу
     */
    override fun start(clientApp: ClientMain) {
        this.clientMain = clientApp
        while (hasNext()) {
            interact(next())
        }
    }

    /**
     * Останавливает выполнение файловых команд
     */
    override fun exit() {
        interactor.exit()
        index = lines.count()
    }

    /**
     * Принимает команды одну за другой и аргументы в строке. Пытается выполнить команду
     *
     * @param stringCommand строку командой
     */
    private fun interact(stringCommand: String) {
        val input = stringCommand.trim().split(" ")
        if (input.count() > 2) {
            throw CommandFileException("Слишком много аргументов в строке")
        }
        try {
            val command = input[0]
            lastArgument = if (input.count() == 2) input[1] else null
            executeCommand(command)
        } catch (e: CommandFileException) {
            throw e
        } catch (e: Throwable) {
            throw CommandFileException(e.message)
        }
    }

    /**
     * Получает аргументы из файлов, проверяя последний аргумент или следующую строку
     *
     * @param argTypes массив с [ArgumentType]
     * @return Любой массив, заполненный необходимыми аргументами
     */
    override fun getArgs(argTypes: Array<ArgumentType>): Array<Any> {
        val args = arrayListOf<Any>()
        argTypes.forEach {
            args.add(
                when (it) {
                    ArgumentType.INT -> getInt()
                    ArgumentType.STRING -> getString()
                    ArgumentType.VEHICLE_TYPE -> getVehicleType()
                    ArgumentType.FUEL_TYPE -> getFuelType()
                }
            )
        }
        return args.toArray()
    }

    override fun getString(): String = lastArgument ?: throw CommandFileException("Нет аргумента")
    override fun getInt(): Int = lastArgument?.toIntOrNull() ?: throw CommandFileException("Не Int")
    override fun getFuelType(): FuelType =
        lastArgument?.let { FuelType.valueOfOrNull(it) } ?: throw CommandFileException("Не тип топлива")

    override fun getVehicleType(): VehicleType =
        lastArgument?.let { VehicleType.valueOfOrNull(it) } ?: throw CommandFileException("Не тип транспорта")

    override fun getVehicle(): Vehicle {
        val name = next()
        val coordinates = Coordinates(
            next().toFloatOrNull() ?: throw CommandFileException("Не Float"),
            next().toDoubleOrNull() ?: throw CommandFileException("Не Double")
        )
        val enginePower = next().toIntOrNull() ?: throw CommandFileException("Не Int")
        val distanceTravelled = next().toLongOrNull()

        val fuelType = FuelType.valueOfOrNull(next()) ?: throw CommandFileException("Не Fuel type`")
        val vehicleType = VehicleType.valueOfOrNull(next()) ?: throw CommandFileException("Не Vehicle Type`")

        return Vehicle(
            name = name,
            coordinates = coordinates,
            enginePower = enginePower,
            distanceTravelled = distanceTravelled,
            fuelType = fuelType,
            vehicleType = vehicleType
        )
    }

    /**
     * @return следующая строка командного файла
     */
    fun next(): String {
        if (hasNext()) return lines[index++]
        throw CommandFileException("Недостаточно строк")
    }

    /**
     * Проверить, есть ли следующая строка командного файла
     *
     * @return true, если это еще не последняя строка
     */
    fun hasNext(): Boolean = (index < lines.count())
}
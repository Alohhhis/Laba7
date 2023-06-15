package commands

import ArgumentType
import CommandResult
import data.Vehicle
import exceptions.ParameterException

/**
 * Класс UpdateCommand отвечает за обновление конкретного автомобиля в коллекции VehicleCollection
 * путем предоставления действительного ID.
 *
 */
class Update : StorageCommand() {
    override fun getArgumentType(): Array<ArgumentType> = arrayOf(ArgumentType.INT, ArgumentType.VEHICLE_TYPE)

    override fun getDescription(): String =
        "update : обновить значение элемента коллекции, id которого равен заданному"

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        previousPair.clear()
        val userKey = args[0] as Int
        val collection = storage.getCollection { true }
        if (userKey !in collection.keys) {
            return CommandResult.Failure("Update", ParameterException("Элемента с таким ключом не существует"))
        }
        previousPair.add(userKey to collection[userKey])
        storage.update(userKey, args[1] as Vehicle)
        return CommandResult.Success("Update")
    }
    override fun undo(): CommandResult {
        previousPair.forEach { (key, value) ->
            storage.update(key, value!!)
        }
        previousPair.clear()
        return CommandResult.Success("Undo Update")
    }
}

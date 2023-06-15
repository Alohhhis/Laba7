package commands

import ArgumentType
import CommandResult
import data.Vehicle
import exceptions.ParametrException


class Insert : StorageCommand() {
    override fun getDescription(): String = "insert : добавить новый элемент с заданным ключом"

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        previousPair.clear()
        val userKey = args[0] as Int
        val collection = storage.getCollection { true }
        if (userKey in collection.keys) {
            return CommandResult.Failure("Insert")
        }
        previousPair.add(userKey to collection[userKey])
        storage.insert(userKey, args[1] as Vehicle)
        return CommandResult.Success("Insert")
    }

    override fun undo(): CommandResult {
        previousPair.forEach { (key) ->
            storage.removeKey(key)
        }
        previousPair.clear()
        return CommandResult.Success("Undo Insert")
    }

    override fun getArgumentType(): Array<ArgumentType> = arrayOf(ArgumentType.INT, ArgumentType.FUEL_TYPE,  ArgumentType.VEHICLE_TYPE)
}
package commands

import ArgumentType
import CommandResult
import data.Vehicle


class RemoveGreater : StorageCommand() {
    override fun getDescription(): String = "remove_greater : удалить из коллекции все элементы, превышающие заданный"

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        val userElement = args[0] as Vehicle
        storage.getCollection { userElement < value }
            .forEach {
                previousPair.add(it.key to it.value)
                storage.removeKey(it.key)
            }
        return CommandResult.Success("Remove_greater")
    }

    override fun undo(): CommandResult {
        previousPair.forEach { (key, value) ->
            storage.insert(key, value!!)
        }
        previousPair.clear()
        return CommandResult.Success("Undo Remove_greater")
    }

    override fun getArgumentType(): Array<ArgumentType> = arrayOf(ArgumentType.VEHICLE_TYPE)
}

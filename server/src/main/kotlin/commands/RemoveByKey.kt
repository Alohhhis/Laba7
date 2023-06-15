package commands

import ArgumentType
import CommandResult
import exceptions.ParametrException

class RemoveByKey : StorageCommand() {
    override fun getDescription(): String = "remove_key : удалить элемент из коллекции по его ключу"

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        previousPair.clear()
        val userKey = args[0] as Int
        val collection = storage.getCollection { true }
        if (userKey !in collection.keys) {
            return CommandResult.Failure("Remove_greater")
        }
        previousPair.add(userKey to collection[userKey])
        storage.removeKey(userKey)
        return CommandResult.Success("Remove_key")
    }

    override fun undo(): CommandResult {
        previousPair.forEach { (key, value) ->
            storage.insert(key, value!!)
        }
        previousPair.clear()
        return CommandResult.Success("Undo Remove_key")
    }

    override fun getArgumentType(): Array<ArgumentType> = arrayOf(ArgumentType.INT)
}


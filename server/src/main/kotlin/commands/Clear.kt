package commands

import ArgumentType
import CommandResult

/**
 * Команда, которая очищает коллекцию
 */
class Clear : StorageCommand() {
    override fun getDescription(): String = "clear : очистить коллекцию"

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        storage.clear()
        return CommandResult.Success("Clear")
    }

    override fun getArgumentType(): Array<ArgumentType> = arrayOf()
    override fun undo(): CommandResult? {
        return null
    }
}
package commands

import ArgumentType
import CommandResult

class Info : StorageCommand() {
    override fun getDescription(): String = "info : вывести в стандартный поток вывода информацию о коллекции"

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        return CommandResult.Success("Info", storage.getInfo())
    }

    override fun getArgumentType(): Array<ArgumentType> = arrayOf()
    override fun undo(): CommandResult? {
        return null
    }
}
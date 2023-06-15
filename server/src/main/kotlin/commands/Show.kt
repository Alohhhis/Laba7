package commands

import ArgumentType
import CommandResult

class Show : StorageCommand() {
    override fun getArgumentType(): Array<ArgumentType> = arrayOf()
    override fun getDescription(): String = "show : вывести все элементы коллекции в строковом представлении"
    override fun undo(): CommandResult? {
        return null
    }


    override fun execute(args: Array<Any>, token: String?): CommandResult {
        val message = buildString {
            appendLine("Содержание коллекции: ")
            storage.getCollection { true }.forEach {
                appendLine("${it.key} = ${it.value}")
            }
        }
        return CommandResult.Success("Show", message)
    }
}
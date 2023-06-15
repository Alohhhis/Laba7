package commands

import ArgumentType
import Command
import CommandResult
import org.koin.core.component.inject
import utils.CommandManager

class Help : Command() {
    private val commandManager: CommandManager by inject()

    override fun getDescription(): String = "help : вывести справку по доступным командам"

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        val message = buildString {
            commandManager.commands.values.forEach {
                appendLine(it.getDescription())
            }
        }
        return CommandResult.Success("Help", message)
    }

    override fun getArgumentType(): Array<ArgumentType> = arrayOf()

}

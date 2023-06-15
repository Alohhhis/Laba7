package commands

import ArgumentType
import CommandResult
import org.koin.core.component.inject
import utils.CommandManager

/**
 * Команда отображает доступные команды
 */
class Help : ClientCommand() {
    private val commandManager: CommandManager by inject()

    override fun getDescription(): String = "help : вывести доступные команды"
    override fun execute(args: Array<Any>, token: String?): CommandResult {
        val message = buildString {
            commandManager.commands.forEach {
                appendLine(it.key)
            }
        }
        return CommandResult.Success("Help", message.trim())
    }

    override fun getArgumentType(): Array<ArgumentType> = arrayOf()
}
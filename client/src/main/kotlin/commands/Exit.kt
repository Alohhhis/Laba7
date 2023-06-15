package commands

import ArgumentType
import CommandResult

/**
 * Команда разрывает соединение с сервером
 */
class Exit : ClientCommand() {
    override fun getDescription(): String = "exit : закрыть соединение с сервером"

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        interactor.exit()
        return CommandResult.Success("Exit")
    }

    override fun getArgumentType(): Array<ArgumentType> = arrayOf()
}
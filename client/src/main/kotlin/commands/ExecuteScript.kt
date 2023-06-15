package commands

import ArgumentType
import CommandResult
import org.jetbrains.kotlin.konan.file.File

/**
 * Команда считывает и выполняет скрипт из указанного файла
 *
 * Ошибка, если файл не найден
 */
class ExecuteScript : ClientCommand() {
    override fun getDescription(): String =
        "execute_script : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме."

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        val path = args[0] as String
        if (!File(path).exists) {
            return CommandResult.Failure("Execute_script")
        }
        try {
            interactor.executeCommandFile(path)
        } catch (e: Throwable) {
            return CommandResult.Failure("Execute_script")
        }
        return CommandResult.Success("Execute_script")
    }

    override fun getArgumentType(): Array<ArgumentType> = arrayOf(ArgumentType.STRING)
}
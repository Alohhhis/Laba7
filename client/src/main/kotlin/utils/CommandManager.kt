package utils

import ArgumentType
import ClientMain
import CommandResult
import Frame
import FrameType
import commands.*
import exceptions.CommandException


/**
 * Класс используется для ссылки на команды
 */
class CommandManager {
    /**
     * Список всех клиентских команд
     */
    private val clientCommands = mapOf(
        "help" to Help(),
        "execute_script" to ExecuteScript(),
        "exit" to Exit(),
        "update_commands" to UpdateCommands(),

    )

    /**
     * Список всех команд. Обновления с помощью update_commands
     */
    val commands = clientCommands.mapValues { e -> e.value.getArgumentType() }.toMutableMap()

    /**
     * Отправляет запрос на сервер, чтобы получить список серверных команд
     *
     * @param clientApp текущий клиент, подключенный к серверу
     * @return true, если запрос был успешным
     */
    fun updateCommands(clientApp: ClientMain): Boolean {
        val frame = Frame(FrameType.LIST_OF_COMMANDS_REQUEST)
        clientApp.sendFrame(frame)
        val respond = clientApp.receiveFrame()
        if (respond.type != FrameType.LIST_OF_COMMANDS_RESPONSE) return false
        val serverCommands = respond.body["commands"] as? Map<String, Array<ArgumentType>> ?: return false
        commands.clear()
        commands.putAll(clientCommands.mapValues { e -> e.value.getArgumentType() })
        commands.putAll(serverCommands)
        return true
    }

    /**
     * Проверяет, исходит ли команда от клиентской части
     *
     * @param command проверена командой
     * @return true, если команда из клиентской части
     */
    private fun isClientCommand(command: String): Boolean = command in clientCommands

    /**
     * Выполнять команды, отправляя фрейм на сервер
     *
     * @param clientApp текущий клиент, подключенный к серверу
     * @return [CommandResult] команды
     */
    fun executeCommand(clientApp: ClientMain, command: String, args: Array<Any>): CommandResult? {
        if (isClientCommand(command)) {
            return clientCommands[command]!!.execute(args)
        }
        val frame = Frame(FrameType.COMMAND_REQUEST)
        frame.setValue("name", command)
        frame.setValue("args", args)
        clientApp.sendFrame(frame)
        val respond = clientApp.receiveFrame()
        return if (respond.type == FrameType.COMMAND_RESPONSE) respond.body["data"] as? CommandResult else null
    }

    /**
     * Получить аргументы, необходимые для команды
     *
     * @param command проверенная команда
     * @return массив [ArgumentType]
     */
    fun getArgs(command: String): Array<ArgumentType> {
        if (command !in commands) {
            throw CommandException("Такой команды не существует")
        }
        return commands[command]!!
    }
}


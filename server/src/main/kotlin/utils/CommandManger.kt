package utils

import Command
import commands.*
import exceptions.CommandException
import org.koin.core.component.KoinComponent

/**
 * Класс используется для ссылки на команды (сервер)
 */
class CommandManager(): KoinComponent {
    val commands = mapOf<String, Command>(
        "info" to Info(),
        "help" to Help(),
        "clear" to Clear(),
        "insert" to Insert(),
        "remove_by_id" to RemoveByKey(),
        "remove_greater" to RemoveGreater(),
        "remove_head" to RemoveHead(),
        "show" to Show(),
        "update" to Update(),
        "login" to Login(),
        "register" to Register()
    )
    /**
     * Проверка, существует ли такая команда
     */
    fun getCommand(name: String): Command {
        val command = commands[name] ?: throw CommandException("Такой команды не существует")
        return command
    }
}
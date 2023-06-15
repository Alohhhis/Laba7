package commands

import ArgumentType
import CommandResult
import data.User
import exceptions.RegistrationExeption
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Register : StorageCommand() {
    override fun undo(): CommandResult? {
        return null
    }

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        if (args.size != 1) {
            throw IllegalArgumentException("Register command expects 1 argument, but got ${args.size}.")
        }

        val user = args[0] as? String
            ?: throw IllegalArgumentException("Argument for register command is not of type String.")

        val deserializedUser: User = Json.decodeFromString(user)

        return if(usersStorage.register(deserializedUser.username, deserializedUser.password)) {
            CommandResult.Success("Register")
        } else {
            CommandResult.Failure("Register", RegistrationExeption("Не удалось зарегистрироваться"))
        }
    }

    override fun getDescription(): String = "register : регистрация нового пользователя"
    override fun getArgumentType(): Array<ArgumentType> = arrayOf()
}
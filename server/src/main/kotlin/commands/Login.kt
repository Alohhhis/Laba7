package commands

import ArgumentType
import CommandResult
import data.User
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Login: StorageCommand() {
    override fun undo(): CommandResult? {
        return null
    }

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        if (args.size != 1) {
            throw IllegalArgumentException("Login command expects 1 argument, but got ${args.size}.")
        }
        val user = args[0] as? String
            ?: throw IllegalArgumentException("Argument for login command is not of type String.")

        val deserializedUser: User = Json.decodeFromString(user)

        val loginResult = usersStorage.login(deserializedUser.username, deserializedUser.password)
        return CommandResult.Success("Login")
    }

    override fun getDescription(): String  = "login : осуществляет вход в аккаунт"
    override fun getArgumentType(): Array<ArgumentType> = arrayOf()
}
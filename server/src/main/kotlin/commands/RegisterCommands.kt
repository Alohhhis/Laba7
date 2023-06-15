package commands

import Command
import CommandResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.StorageManager
import utils.UsersStorage

abstract class RegisterCommands: Command() {
    val storageManager: StorageManager by inject()
    val usersStorage: UsersStorage by inject()
     abstract fun execute(args: List<Any>, token: String? = null): CommandResult
    /**
     * @return a description of the command.
     */
    abstract override fun getDescription(): String

}
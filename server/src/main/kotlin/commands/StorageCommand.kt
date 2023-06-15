package commands

import Command
import CommandResult
import data.Vehicle
import org.koin.core.component.inject
import utils.Storage
import utils.UsersStorage
import java.util.LinkedHashMap


abstract class StorageCommand : Command() {
    val usersStorage: UsersStorage by inject()
    val storage: Storage<LinkedHashMap<Int, Vehicle>, Int, Vehicle> by inject()
    val previousPair: MutableList<Pair<Int, Vehicle?>> = mutableListOf()
    abstract fun undo(): CommandResult?
}
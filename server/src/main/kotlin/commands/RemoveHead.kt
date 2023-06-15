package commands

import ArgumentType
import CommandResult

/**
 * Класс RemoveHeadCommand удаляет и возвращает первый элемент в коллекции Vehicle VehicleCollection. *
 */
class RemoveHead : StorageCommand() {
    override fun undo(): CommandResult {
        previousPair.forEach { (key, value) ->
            storage.insert(key, value!!)
        }
        previousPair.clear()
        return CommandResult.Success("Undo Remove_greater")
    }

    override fun execute(args: Array<Any>, token: String?): CommandResult {
        val message = buildString {
            appendLine("Первый элемент коллекции: ")
            val firstItem = storage.getCollection { true }.entries.firstOrNull()
            if (firstItem != null) {
                appendLine("${firstItem.key} = ${firstItem.value}")
                storage.getCollection { true }.entries.remove(firstItem)
            } else {
                appendLine("Коллекция пуста")
            }
        }
        return CommandResult.Success("Remove head", message)

    }

    override fun getDescription(): String = "remove_greater :  вывести первый элемент коллекции и удалить его"

    override fun getArgumentType(): Array<ArgumentType> = arrayOf(ArgumentType.VEHICLE_TYPE)


}

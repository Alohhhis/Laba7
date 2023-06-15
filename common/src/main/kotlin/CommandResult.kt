import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * Закрытый класс для результатов выполнения команд.
 *
 * Может быть либо [Success], либо [Failure]
 */
sealed class CommandResult {
    /**
     * При успешном выполнении команды
     *
     * @param CommandName - имя выполняемой команды
     * @param текст сообщения для пользователя
     */
    @Serializable
    data class Success(val commandName: String, val message: String? = null) : CommandResult()

    /**
     * При неправильном выполнении команды
     *
     * @param CommandName имя (не) выполненной команды
     * @param генерирует исключение, которое произошло
     */
    @Serializable
    data class Failure(val commandName: String) : CommandResult()
}
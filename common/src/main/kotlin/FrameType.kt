import kotlinx.serialization.Serializable

/**
 * enum класс содержит возможные типы фреймов
 */
@Serializable
enum class FrameType {
    COMMAND_REQUEST,
    LIST_OF_COMMANDS_REQUEST,
    COMMAND_RESPONSE,
    LIST_OF_COMMANDS_RESPONSE
}
import kotlinx.serialization.Serializable

/**
 * Перечисление представляет возможные аргументы команд
 */
@Serializable
enum class ArgumentType {
    INT,
    STRING,
    FUEL_TYPE,
    VEHICLE_TYPE
}
package data

import kotlinx.serialization.Serializable

/**
 * Класс, который содержит типы транспортных средств
 */
@Serializable
enum class VehicleType {
    CAR,
    SUBMARINE,
    SHIP,
    BICYCLE,
    HOVERBOARD;
    companion object {
        /**
         * Decorates the [valueOf] by comparing with [values]
         *
         * @param name take the string that should be converted to [VehicleType]
         * @return [VehicleType] or null if string can't be converted
         */
        fun valueOfOrNull(name: String): VehicleType? {
            return values().firstOrNull { it.name == name }
        }
    }
}
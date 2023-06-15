package data

import kotlinx.serialization.Serializable

/**
 * Класс Содержащий тип топлива
 */
@Serializable
enum class FuelType {
    ELECTRICITY,
    NUCLEAR,
    PLASMA;
    companion object {
        fun valueOfOrNull(name: String): FuelType? {
            return values().firstOrNull { it.name == name }
        }
    }
}
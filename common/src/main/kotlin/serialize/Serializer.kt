package serialize
/**
 * Интерфейс для сериализации [LinkedHashMap]
 */
interface Serializer<T> {
    /**
     * Сериализует объект в [String]
     *
     * @param collection объект, который будет сериализован
     * @return сериализованную строку [String]
     */
    fun serialize(collection: T): String

    /**
     * Десериализует объект из строки [String]
     *
     * @param serialized сериализованная строка [String]
     * @return десриализованный объект (T)
     */
    fun deserialize(serialized: String): T
}
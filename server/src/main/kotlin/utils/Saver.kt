package utils

/**
 * Сохраняет и загружает [LinkedHashMap] коллекцию
 */
interface Saver<T> {
    /**
     * Сохраняет [collection] для загрузки в будущем
     *
     * @param collection, которая должна быть сохранена
     */
    fun save(collection: T)

    /**
     * Загружает сохраненную коллекцию [LinkedHashMap]
     *
     * @return загруженную ранее сохраненную коллекцию
     */
    fun load(): T
}
package serialize

import data.Vehicle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

/**
 * Реализует интерфейс [Serializer] с сериализацией JSON для [LinkedHashMap]
 */
class SerializeManager : Serializer<LinkedHashMap<Int, Vehicle>> {
    private val module = SerializersModule {
        contextual(LocalDateSerializer)
    }

    private val serializer = Json { serializersModule = module }

    override fun serialize(collection: LinkedHashMap<Int, Vehicle>) =
        serializer.encodeToString(collection)

    override fun deserialize(serialized: String) =
        serializer.decodeFromString<LinkedHashMap<Int, Vehicle>>(serialized)
}
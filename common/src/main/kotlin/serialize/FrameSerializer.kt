package serialize

import Frame
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

class FrameSerializer: Serializer<Frame> {
    private val module = SerializersModule {
        contextual(LocalDateSerializer)
        contextual(KAnySerializer)
        contextual(KThrowableSerializer)
    }

private val serializer = Json {serializersModule = module}
    override fun serialize(collection: Frame): String = serializer.encodeToString(collection)

    override fun deserialize(serialized: String): Frame = serializer.decodeFromString(serialized)

}
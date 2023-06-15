import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * Класс, используемый для передачи информации между сервером и клиентом
 *
 * @param type [FrameType], которым является фрейм
 */
@Serializable
class Frame(val type: FrameType) {

    /**
     * Контекст фрейма
     */
    val body = mutableMapOf<String, @Contextual Any>()

    /**
     * Используется при добавлении параметров в  [body]
     */
    fun setValue(key: String, value: Any) {
        body[key] = value
    }
}

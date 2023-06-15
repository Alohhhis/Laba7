package utils

import java.security.MessageDigest
import kotlin.random.Random

class HashingPassword {
    // Функция для генерации случайной строки
    fun generateSalt(length: Int = 16): String {
        val symbols = ('0'..'9') + ('a'..'z') + ('A'..'Z')
        return (1..length)
            .map { Random.nextInt(0, symbols.size) }
            .map(symbols::get)
            .joinToString("")
    }

    // Функция для хэширования пароля с солью
    fun hashPassword(password: String, salt: String? = null): String {
        val bytes = password.toByteArray() + (salt ?: generateSalt()).toByteArray()
        val md = MessageDigest.getInstance("SHA-384")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })

    }
}
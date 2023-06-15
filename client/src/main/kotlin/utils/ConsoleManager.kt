package utils.console

import utils.ReaderWriter
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

/**
 * Реализует [ReaderWriter] путем взаимодействия с консолью с помощью встроенных методов.
 */
class ConsoleManager : ReaderWriter {
    private val inputStream: InputStream = System.`in`
    private val reader = InputStreamReader(inputStream, Charset.forName("UTF-8"))

    override fun readLine(): String = BufferedReader(reader).readLine()
    override fun writeLine(text: String) = println(text)
    override fun write(text: String) = print(text)
        //проверка вводимых значений
    override fun getValidatedValue(message: String, validator: (String) -> Boolean): String {
        write(message)
        while (true) {
            val userInput = readLine()
            if (validator(userInput)) {
                return userInput
            }

            write("Попробуйте ещё раз: ")
        }
    }
}
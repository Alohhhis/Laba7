package utils

interface ReaderWriter {
    /**
     * Читает строку от пользователя
     *
     * @return [String] строку, введённую пользователем
     */
    fun readLine(): String

    /**
     * Отображает сообщение пользователю, добавляе новую строку в конец
     *
     * @param text содержание сообщения пользователю
     */
    fun writeLine(text: String)

    /**
     * Отображает сообщение пользователю, не добавляет новую строку в конец
     *
     * @param text содержание сообщения пользователю
     */
    fun write(text: String)

    /**
     * Повторяет процесс ввода до тех пор, пока он не удовлетворит валидатор
     *
     * @param message отображается один раз перед вводом
     * @param validator - это условие, которому должны удовлетворять входные данные
     */
    fun getValidatedValue(message: String, validator: (String) -> Boolean): String
}
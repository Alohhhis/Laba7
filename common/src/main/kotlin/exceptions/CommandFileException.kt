package exceptions

/**
 * @exception [CommandFileException] используется в случае неудовлетворительного содержимого командного файла
 */
class CommandFileException(message: String?) : Throwable("Error in the command file: $message")
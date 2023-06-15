package exceptions

/**
 * @exception [FileException] используется, если файл не может быть открыт или найден
 */
class FileException (message: String?) : Throwable(message)

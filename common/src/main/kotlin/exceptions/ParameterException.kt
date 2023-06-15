package exceptions

/**
 * @exception [ParameterException] используется, если параметр не удовлетворяет условию
 */
class ParameterException(message: String?) : Throwable(message)
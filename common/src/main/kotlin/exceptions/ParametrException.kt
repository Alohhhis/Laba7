package exceptions

/**
 * @exception [ParameterException] используется, если параметр не удовлетворяет условию
 */
class ParametrException(message: String?) : Throwable(message)
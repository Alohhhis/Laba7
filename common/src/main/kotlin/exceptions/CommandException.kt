package exceptions

/**
 * @exception [CommandException] вызывается в случае, если комманда не найдена
 */
class CommandException(message: String?) : Throwable(message)

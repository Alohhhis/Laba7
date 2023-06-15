package utils

import java.util.Scanner

class UserCreatorReader {
    private val scanner = Scanner(System.`in`)
    private val hashMan = HashingPassword()
    private fun readUsername():String{
        println("Введите имя пользователя: ")
        return scanner.nextLine()
    }

    private fun readUserPassword(): String{
        println("Введите пароль: ")
        return scanner.nextLine().toString().let { hashMan.hashPassword(it) }
    }
}
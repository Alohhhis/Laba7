package utils
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

class DataBaseManager {
    var connection: Connection? = null
        private set
init {
    val lines = File("data_base_info.txt").readLines()
    val url = lines[0]
    val user = lines[1]
    val password =lines[2]
    connection = DriverManager.getConnection(url, user, password)
    println("successfully connected to the PostgresSQL")
}
}
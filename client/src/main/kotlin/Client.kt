import mu.KotlinLogging
import org.koin.core.context.GlobalContext.startKoin


/**
 * Main
 */
fun main() {
    startKoin {
        modules(clientModule)
    }

    val logger = KotlinLogging.logger {}
    // this should make reconnect to the server possible
    // connect - tries to reconnect
    // exit    - stops the application
    var command = "connect"

    while (command != "exit") {
        if (command == "connect") {
            ClientMain("localhost", 2222).start()
            logger.info {"Клиент закрылся"}
        }
        println(command)
        print("connect or exit: ")
        command = readln().trim()
    }
}


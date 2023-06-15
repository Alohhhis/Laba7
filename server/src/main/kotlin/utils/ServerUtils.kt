package utils

import ServerMain
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.SocketChannel
import kotlin.concurrent.thread

class Timer(val milli: Long) {
    fun onTimerOn(actions: () -> Unit) {
        thread {
            while (true) {
                Thread.sleep(milli)
                actions()
            }
        }
    }
}

class ServerWrapper {
    var port: Int = 2228
    lateinit var connect: (SelectionKey, Selector) -> Unit
    lateinit var disconnect: (SelectionKey, SocketChannel) -> Unit
    lateinit var server: ServerMain
    fun init() {
        server = ServerMain(port)
        server.onConnect = connect
        server.onDisconnect = disconnect
    }

    fun onConnect(actions: (SelectionKey, Selector) -> Unit) {
        connect = actions
    }

    fun onDisconnect(actions: (SelectionKey, SocketChannel) -> Unit) {
        disconnect = actions
    }

    fun timerAction(milliseconds: Long, receiver: Timer.() -> Unit) {
        val timer = Timer(milliseconds)
        timer.receiver()
    }
}

fun server(config: ServerWrapper.() -> Unit): ServerMain {
    val serverWrapper = ServerWrapper()
    serverWrapper.config()
    serverWrapper.init()
    return serverWrapper.server
}



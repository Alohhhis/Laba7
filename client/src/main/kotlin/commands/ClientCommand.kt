package commands

import Command
import org.koin.core.component.inject
import utils.Interactor

/**
 * Абстрактный класс для клиентских команд, который не отправляется на сервер
 */
abstract class ClientCommand : Command() {
    val interactor: Interactor by inject()
}
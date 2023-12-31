import org.koin.dsl.module
import serialize.FrameSerializer
import serialize.Serializer
import utils.*
import utils.console.ConsoleManager

val clientModule = module {
    factory<ReaderWriter> {
        ConsoleManager()
    }

    factory<Serializer<Frame>> {
        FrameSerializer()
    }

    factory<Validator> {
        ValidationManager(interactor = get(), userManager = get())
    }

    single {
        CommandManager()
    }

    factory {
        FileManager()
    }

    factory {
        FrameSerializer()
    }

    single<Interactor> {
        InteractionManager(userManager = get(), fileManager = get(), commandManager = get())
    }

}
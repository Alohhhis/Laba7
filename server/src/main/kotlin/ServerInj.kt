package di
import FileManager
import ServerMain
import data.Vehicle
import org.koin.dsl.module
import serialize.Serializer
import utils.*
import utils.StorageManager
import serialize.SerializeManager

val serverModule = module {
    factory<Saver<LinkedHashMap<Int, Vehicle>>> {
        FileSaver("save.txt", serializer = get(), fileManager = get())
    }
    factory<Serializer<LinkedHashMap<Int, Vehicle>>> {
        SerializeManager()
    }

    factory {
        FileManager()
    }
    single<Storage<LinkedHashMap<Int, Vehicle>, Int, Vehicle>> {
        StorageManager()
    }
    single {
        CommandManager()
    }
    single { ServerMain(2228) }
}
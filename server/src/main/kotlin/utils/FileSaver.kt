package utils

import FileManager
import data.Vehicle
import serialize.SerializeManager
import serialize.Serializer

class FileSaver(
    private val pathToSaveFile: String = "save.txt",
    private val serializer: Serializer<LinkedHashMap<Int, Vehicle>> = SerializeManager(),
    private val fileManager: FileManager = FileManager()
)
    : Saver<LinkedHashMap<Int, Vehicle>> {
    override fun load(): LinkedHashMap<Int, Vehicle> =
        serializer.deserialize(fileManager.readFile(pathToSaveFile))

    override fun save(collection: LinkedHashMap<Int, Vehicle>) {
        fileManager.writeFile(pathToSaveFile, serializer.serialize(collection))
    }
}
   

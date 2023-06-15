import exceptions.FileException
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.util.Scanner

class FileManager {


    /**
     * Построчно читает файл по заданному пути
     *
     * @throws [FileException] если файл не удаётся открыть
     * @param path до файла
     */
    fun readFile(path: String): String{ //чтение данных из файла по указанному пути
    // создается новый объект класса File, инициализированный с помощью параметра path.
    // Затем вызывается метод canRead() на этом объекте.
        if (!File(path).canRead()){
            throw FileException("Файл не открывается")
        }
        val scanner = Scanner(File(path))
        return buildString {
            while (scanner.hasNextLine()){
                append(scanner.nextLine())
                if (scanner.hasNextLine()) append("\n")
            }
        }
    }

    fun writeFile(path: String, text: String) {
        FileOutputStream(path).use { fos ->
            OutputStreamWriter(fos, Charsets.UTF_8).use { osw ->
                BufferedWriter(osw).use { bf -> bf.write(text) }
            }
        }
    }
}
package utils

import data.Vehicle
import java.time.LocalDate

class StorageManager: Storage<LinkedHashMap<Int, Vehicle>, Int, Vehicle> {
    private val date: LocalDate = LocalDate.now()
    val vehicleCollection = LinkedHashMap<Int, Vehicle>()

    override fun clear() {
        vehicleCollection.clear()
    }

    override fun removeKey(id: Int) {
        vehicleCollection.remove(id)
    }

    override fun getInfo(): String {
        return "Коллекция  ${this.javaClass} \n" +
                "тип: LinkedHashMap количество элементов  ${vehicleCollection.size} \n" +
                "дата инициализации $date"
    }

    override fun insert(id: Int, element: Vehicle) {
        vehicleCollection[id] = element
    }


    override fun getCollection(predicate: Map.Entry<Int, Vehicle>.() -> Boolean): LinkedHashMap<Int, Vehicle> =
        LinkedHashMap(vehicleCollection.filter(predicate))

    override fun update(id: Int, element: Vehicle) {

    }
}
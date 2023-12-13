import model.Usecase1
import java.io.File
import java.util.*

class Solution_Usecase_1 {
    init {
        println("--------------------------Usecase 1------------------------------")
        val input = Scanner(File("src/main/resources/usecase/Use_Case_1.txt"))
        input.useDelimiter("[-\n]")

        var listUsecaseData = emptyList<Usecase1>()
        var isFirstData = true
        while (input.hasNext()) {
            if (isFirstData) {
                isFirstData = false
                input.next()
                continue
            } else {
                val dataInput = input.next()
                val dataSplited = dataInput.split("|")
            }
        }
    }
}
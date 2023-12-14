import model.Bureau
import model.Usecase1
import java.io.File
import java.util.*
import kotlin.math.min

class Solution_Usecase_1 {
    init {
        println("--------------------------Usecase 1------------------------------")
        val input = Scanner(File("src/main/resources/usecase/Use_Case_1.txt"))
        var listUsecaseData = emptyList<Usecase1>()
        var isFirstData = true

        while (input.hasNext()) {
            if (isFirstData) {
                isFirstData = false
                input.next()
                continue
            } else {
                val dataInput = input.next()
                val dataSplited = dataInput.split(",")
                val listBureau = dataSplited.subList(3, dataSplited.size).chunkArrayList(3)
                val usecase1 = Usecase1(
                    jeniskredit = dataSplited[0],
                    kreditNo = dataSplited[1].toInt(),
                    active = dataSplited[2],
                    listBureau = listBureau
                )

                listUsecaseData = listUsecaseData + usecase1
            }
        }

        getNumberActiveFacilities(listUsecaseData)
    }

    private fun getNumberActiveFacilities(data: List<Usecase1>) {
        var activeFacility = listOf<Bureau>()
        data.forEach {
            if (it.active != "Y") return@forEach
            it.listBureau.forEachIndexed { index, bureau ->
                // DPD = tahunbulan01ht - tahunbulan24ht
                val DPD = it.listBureau[index].DPD - it.listBureau[it.listBureau.lastIndex - index].DPD

                // Collectability = tahunBulan01Kol - tahunBulan01Kol
                val COL = it.listBureau[index].COL - it.listBureau[it.listBureau.lastIndex - index].COL
                if (DPD > 30 || COL > 2) {
                    activeFacility = activeFacility + bureau
                }
            }
        }

        println("Size of Active Facilities : ${activeFacility.size}")
        println("Result of Active Facilities : $activeFacility")
    }

    private fun List<String>.chunkArrayList(chunkSize: Int): List<Bureau> {
        val chunkList: MutableList<Bureau> = ArrayList()
        var i = 0
        while (i < size) {
            val end = min((i + chunkSize).toDouble(), size.toDouble()).toInt()
            val dataSublist = subList(i, end)
            chunkList.add(
                Bureau(
                    DPD = dataSublist[0].toInt(),
                    Year = dataSublist[1].toInt(),
                    COL = dataSublist[2].toInt(),
                )
            )
            i += chunkSize
        }
        return chunkList
    }
}
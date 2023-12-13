import model.Gender
import model.Occupation
import model.RiskSegment
import model.Usecase3
import java.io.File
import java.util.*
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.roundToInt

class Solution_Usecase_3 {
    init {
        println("--------------------------Usecase 3------------------------------")
        val input = Scanner(File("src/main/resources/usecase/Use_Case_3.txt"))
        input.useDelimiter("[-\n]")

        var listUsecaseData = emptyList<Usecase3>()
        var isFirstData = true
        while (input.hasNext()) {
            if (isFirstData) {
                isFirstData = false
                input.next()
                continue
            } else {
                val dataInput = input.next()
                val dataSplited = dataInput.split("|")
                listUsecaseData = listUsecaseData + Usecase3(
                    CIF = dataSplited[0].toInt(),
                    MonthlyIncome = dataSplited[1].toInt(),
                    Age = dataSplited[2].toInt(),
                    Gender = Gender.getGender(dataSplited[3]),
                    Occupation = Occupation.getOccupation(dataSplited[4]),
                )
            }
        }

        findTotalScore(listUsecaseData)
    }

    private fun findTotalScore(data: List<Usecase3>) {
        val intercept = 33.53
        data.forEachIndexed { index, it ->
            val ageScore = getScoreByAge(it.Age) * 1.24
            val genderScore = it.Gender.score * 9.24
            val occupationScore = it.Occupation.score * 7.34
            val totalScore = intercept + ageScore + genderScore + occupationScore
            println("$index Result Total Score: ${String.format("%.2f", totalScore)}")

            val riskSegment = getRiskSagmentByScore(totalScore)
            println("-Result Risk Segment: ${riskSegment.description}")

            val facilityLimit = getFacilityLimit(it, riskSegment)
            println("-Result Facility Limit: ${String.format("%.2f", facilityLimit)}")
        }
    }

    private fun getFacilityLimit(data: Usecase3, riskSegment: RiskSegment): Double {
        return when(data.Occupation) {
            Occupation.Employee -> data.MonthlyIncome * riskSegment.employMultiplier
            else -> data.MonthlyIncome * riskSegment.selfEmployMultiplier
        }
    }

    private fun getRiskSagmentByScore(totalScore: Double): RiskSegment {
        return when {
            totalScore < 325 -> RiskSegment.VHR2
            totalScore in 325.0..349.0 -> RiskSegment.VHR
            totalScore in 350.0..369.0 -> RiskSegment.HR
            totalScore in 370.0..374.0 -> RiskSegment.MR
            totalScore in 375.0..378.0 -> RiskSegment.LR
            else -> RiskSegment.VLR
        }
    }

    private fun getScoreByAge(age: Int): Double {
        return when {
            age <= 20 -> 13.45
            age in 21..30 -> 17.88
            age in 31..50 -> 18.98
            else -> 19.33
        }
    }
}
import model.FacilityType
import model.Usecase2
import utils.emptyStringToInt
import utils.orZero
import java.io.File
import java.util.Scanner
import kotlin.math.pow

class Solution_Usecase_2 {
    init {
        println("--------------------------Usecase 2------------------------------")
        val input = Scanner(File("src/main/resources/usecase/Use_Case_2.txt"))
        input.useDelimiter("[-\n]")

        var listUsecaseData = emptyList<Usecase2>()
        var isFirstData = true
        while (input.hasNext()) {
            if (isFirstData) {
                isFirstData = false
                input.next()
                continue
            } else {
                val dataInput = input.next()
                val dataSplited = dataInput.split("|")
                listUsecaseData = listUsecaseData + Usecase2(
                    CIF = dataSplited[0].toInt(),
                    MonthlyIncome = dataSplited[1].toInt(),
                    FacilityType = FacilityType.getFacilityType(dataSplited[2]),
                    Plafond = dataSplited[3].toInt(),
                    Outstanding = dataSplited[4],
                    DueDate = dataSplited[5].emptyStringToInt(),
                    FacilityStartDate = dataSplited[6],
                    PercentageOfAnnualInterestRate = dataSplited[7].toFloat(),
                    UsageType = dataSplited[8],
                    Status = dataSplited[9],
                    Tenor = dataSplited[10].emptyStringToInt(),
                    // Installment = dataSplited[11].toFloat(),
                )
            }
        }

        val installmentList = getMonthlyInstallmentAmount(listUsecaseData)
        getPercentageDBR(installmentList)
    }


    private fun getMonthlyInstallmentAmount(data: List<Usecase2>): List<Usecase2> {
        return data.mapIndexed { index, it ->
            if (it.FacilityType == FacilityType.CC) {
                val monthlyInstallmentAmount = 0.1f * it.Plafond.orZero()

                println("$index Result Find Monthly Installment Amount: ${String.format("%.2f", monthlyInstallmentAmount)}")
                return@mapIndexed it.copy(Installment = monthlyInstallmentAmount)
            }
            val rate = 1 + it.PercentageOfAnnualInterestRate.orZero()
            val ratePow = rate.toDouble().pow(it.Tenor.orZero().toDouble())
            val interestRate = it.PercentageOfAnnualInterestRate.orZero() * ratePow
            val interestRateMinusOne = ratePow - 1
            val plafond = it.Plafond.orZero()
            val monthlyInstallmentAmount = (plafond * interestRate) / interestRateMinusOne

            println("$index Result Find Monthly Installment Amount: ${String.format("%.2f", monthlyInstallmentAmount)}")
            it.copy(Installment = monthlyInstallmentAmount.toFloat())
        }
    }

    private fun getPercentageDBR(installmentList: List<Usecase2>) {
        var totalMonthlyInstallment = 0f
        installmentList.forEach {
            totalMonthlyInstallment += it.Installment.orZero()
        }
        installmentList.forEachIndexed { index, usecase2 ->
            val dbr = totalMonthlyInstallment / usecase2.MonthlyIncome.orZero()
            println("Result PercentageDBR : ${String.format("%.2f", dbr)}")
        }
    }
}
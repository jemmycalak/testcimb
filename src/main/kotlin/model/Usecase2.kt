package model

data class Usecase2(
    val CIF: Int? = null,
    val MonthlyIncome: Int? = null,
    val FacilityType: FacilityType? = null,
    val Plafond: Int? = null,
    val Outstanding: String? = "",
    val DueDate: Int? = null,
    val FacilityStartDate: String? = "",
    val PercentageOfAnnualInterestRate: Float? = null,
    val UsageType: String? = "",
    val Status: String? = "",
    val Tenor: Int? = null,
    val Installment: Float? = null,
)

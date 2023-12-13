package model

enum class FacilityType(val description: String) {
    CC("Credit Card"),
    PL("Personal Loan"),
    M("Mortgage");

    companion object {
        fun getFacilityType(value: String) =
            entries.find { it.description == value }
    }
}
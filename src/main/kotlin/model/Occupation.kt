package model

enum class Occupation(val score: Double) {
    Employee(12.45),
    Entrepreneur(9.34),
    Others(6.78);

    companion object {
        fun getOccupation(value: String) =
            entries.firstOrNull { it.name == value } ?: Others
    }
}
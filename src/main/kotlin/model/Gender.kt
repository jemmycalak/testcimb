package model

enum class Gender(val score: Double) {
    Male(24.44),
    Female(21.89);

    companion object {
        fun getGender(value: String) =
            entries.first { it.name == value }
    }
}
package model

data class Usecase1(
    val jeniskredit: String,
    val kreditNo: Int,
    val active: String,
    val listBureau: List<Bureau> = emptyList(),
)

data class Bureau(
    val COL: Int,
    val Year: Int,
    val DPD: Int,
)

package utils

fun String.emptyStringToInt(): Int {
    return if (isNullOrEmpty()) 0 else this.toInt()
}
fun Float?.orZero(): Float {
    return this ?: 0f
}
fun Int?.orZero(): Int {
    return this ?: 0
}
fun Double?.orZero(): Double {
    return this ?: 0.0
}

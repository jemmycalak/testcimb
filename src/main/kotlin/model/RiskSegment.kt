package model

enum class RiskSegment(
    val description: String,
    val employMultiplier: Double,
    val selfEmployMultiplier: Double
) {
    VHR2("Very High Risk 2", 0.0, 0.0),
    VHR("Very High Risk", 1.5, 1.0),
    HR("High Risk", 2.0, 1.3),
    MR("Medium Risk", 3.5, 1.5),
    LR("Low Risk", 6.0, 3.0),
    VLR("Very Low Risk", 8.0, 4.0),
}
package pes.shennoter

data class Info(
    val `data`: Data,
    val errmsg: String,
    val errno: Int,
    val stack: Any,
    val trace_id: String
)

data class Data(
    val balance: Double,
    val coldSubsidy: String,
    val dormitory: String,
    val electricitySubsidy: String,
    val hotSubsidy: String,
    val notice: String
)
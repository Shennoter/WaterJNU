package pes.shennoter

data class ErrInfo(
    val `data`: String,
    val errmsg: String,
    val errno: Int,
    val stack: String,
    val trace_id: String
)
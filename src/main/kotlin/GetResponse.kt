package pes.shennoter

import okhttp3.OkHttpClient
import okhttp3.Request

fun getRes(url: String): String? {
    val builder = OkHttpClient.Builder()
    val client = builder //构造client
        .build()
    val request: Request = Request.Builder() //构造request
        .url(url)
        .get()
        .build()
    val response = try {
        client.newCall(request).execute()
    }
    catch (e:Exception){
        return "网络请求发起错误"
    }
    val body = response.body?.string()
    response.close()
    return body
}
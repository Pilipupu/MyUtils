package httpclient

import json.jsonDecode
import okhttp3.*
import org.junit.jupiter.api.Test
import java.io.File


class HttpClientTest {
    @Test
    fun testHttpClient() {
        val client = OkHttpClient().newBuilder()
            .build()
        val body: RequestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("username", "root")
            .addFormDataPart("hostname", "100.66.66.68")
            .addFormDataPart(
                "privatekey", "file",
                RequestBody.create(
                    MediaType.parse("application/octet-stream"),
                    File("/Users/jingwang/id_rsa")
                )
            )
            .build()
        val request  = Request.Builder()
            .url("http://zops-dev:8888")
            .method("POST", body)
            .build()
        val response: Response = client.newCall(request).execute()
        val a = String(response.body()!!.bytes())
        println(a)
        val b = """{"id": "null", "status": "Unable to connect to 100.66.66.68:22", "encoding": "null"}"""
        b.jsonDecode(WebSshStruct::class)
    }
}

class WebSshStruct {
    var id: String? = null
    var status: String? = null
    var encoding: String? = null
}
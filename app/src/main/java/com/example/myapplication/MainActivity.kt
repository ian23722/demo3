package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    const val BASE_URL = "http://192.168.86.37:8080/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {
        val username = findViewById<EditText>(R.id.username_text_edit).text.toString()
        val password = findViewById<EditText>(R.id.password_edit).text.toString()
        val client: AsyncHttpClient = AsyncHttpClient()
        val resultView = findViewById<TextView>(R.id.result_text)

        val url = "http://192.168.86.37:8080/user/login"
        val requestParams : RequestParams = RequestParams()
        requestParams.put("username", username)
        requestParams.put("password", password)
        client.post(url, requestParams, MyResponseHandler(resultView))
    }
}

class MyResponseHandler(private val resultView: TextView) : JsonHttpResponseHandler() {
    override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
        super.onSuccess(statusCode, headers, response)
        resultView.text = response.toString()
    }
}

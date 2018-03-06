package com.dudu.project.lovelycatgank.http.bean

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder
import java.util.logging.Handler

/**
 * Created by admin on 2018/3/4.
 */
object HttpClient {
    private var handler : Handler ? = null
    private var result :String ? = null

    fun httpGet(strUrlPath:String ,params :Map<String,String>,encode:String) :String{
        var strUrlPath  = strUrlPath
        var result :String ? = null
//        val append_url = getRequestData(params,encode)
//        strUrlPath = strUrlPath + "?"+append_url
        try {
            val url = URL(strUrlPath)
            val urlConn = url.openConnection() as HttpURLConnection
            urlConn.connectTimeout = 5000
            urlConn.setRequestProperty("Content-Type","application/dudu-www-form-urlencoded")
            val `in` = InputStreamReader(urlConn.inputStream)
            val buffer = BufferedReader(`in`)
            var inputLine :String ? = null
            result = ""

            while (buffer.readLine().apply { inputLine = this } != null) {
                result += inputLine!! + "\n"
            }

            `in`.close()
            urlConn.disconnect()
        }catch (e : MalformedURLException){
            e.printStackTrace()
        }catch (ioe : IOException){
            ioe.printStackTrace()
        }
        return result!!

    }

    private fun getRequestData(params: Map<String,String>,encode:String) :StringBuffer {
       val stringBuffer = StringBuffer()
        try {
            for ((key,value) in params) {
                stringBuffer.append(key)
                        .append("=")
                        .append(URLEncoder.encode(value,encode))
                        .append("&")
            }
            stringBuffer.deleteCharAt(stringBuffer.length -1)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return stringBuffer
    }
}
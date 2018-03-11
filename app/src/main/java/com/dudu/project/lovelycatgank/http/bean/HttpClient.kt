package com.dudu.project.lovelycatgank.http.bean

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
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
    private val getGankInfoUrl = "http://gank.io/api/random/data/?"

    fun httpGet(infoType :String,curPage:Int) :String{
        var result :String ? = null
        val append_url = getRequestData(infoType,curPage).toString()
        try {
            val url = URL(append_url)
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

    private fun getRequestData(infoType :String,curPage:Int) :StringBuffer {
       val stringBuffer = StringBuffer()
        try {
                stringBuffer.append(getGankInfoUrl)
                        .append(infoType)
                        .append("/")
                        .append("20/")
                        .append(curPage)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return stringBuffer
    }
}
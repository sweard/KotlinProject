package com.jeff.kotlinproject.utils

import java.security.MessageDigest
import kotlin.experimental.and

/**
 * Created by Administrator on 2016-06-24.
 */
object MD5Util {

    fun getMD5String(s: String): String? {
        val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
        try {
            val btInput = s.toByteArray()
            //获得MD5摘要算法的 MessageDigest 对象
            val mdInst = MessageDigest.getInstance("MD5")
            //使用指定的字节更新摘要
            mdInst.update(btInput)
            //获得密文
            val md = mdInst.digest()
            //把密文转换成十六进制的字符串形式
            val j = md.size
            val str = CharArray(j * 2)
            var k = 0
            for (i in 0..j - 1) {
                val byte0 = md[i]
                str[k++] = hexDigits[(byte0.toInt().ushr(4)).and (0xf)]
                str[k++] = hexDigits[byte0.and (0xf).toInt()]
            }
            return String(str)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    fun getLowerMD5(s: String): String? {
        val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
        try {
            val btInput = s.toByteArray()
            //获得MD5摘要算法的 MessageDigest 对象
            val mdInst = MessageDigest.getInstance("MD5")
            //使用指定的字节更新摘要
            mdInst.update(btInput)
            //获得密文
            val md = mdInst.digest()
            //把密文转换成十六进制的字符串形式
            val j = md.size
            val str = CharArray(j * 2)
            var k = 0
            for (i in 0..j - 1) {
                val byte0 = md[i]
                /*str[k++] = hexDigits[byte0.ushr(4) and 0xf]
                str[k++] = hexDigits[byte0 and 0xf]*/
                str[k++] = hexDigits[(byte0.toInt().ushr(4)).and (0xf)]
                str[k++] = hexDigits[byte0.and (0xf).toInt()]
            }
            return String(str)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }
}

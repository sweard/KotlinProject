package com.jeff.kotlinproject.utils

import android.os.Debug
import android.os.Environment
import android.util.Log

import com.jeff.kotlinproject.BuildConfig

import java.io.File
import java.io.PrintWriter
import java.io.StringWriter

/**
 * Created by .F on 2017/5/16.
 * Log工具类 thx @author 李玉江[QQ:1032694760]
 */

object LogUtils {
    private val MIN_STACK_OFFSET = 3// starts at this class after two native calls
    private val MAX_STACK_TRACE_SIZE = 131071 //128 KB - 1
    private val METHOD_COUNT = 2 // show method count in trace
    private val isDebug = BuildConfig.DEBUG// 是否调试模式
    private val debugTag = "KotlinProject"// LogCat的标记

    /**
     * Verbose.

     * @param message the message
     */
    fun verbose(message: String) {
        verboseRoot("", message)
    }

    /**
     * Verbose.

     * @param object  the object
     * *
     * @param message the message
     */
    fun verbose(`object`: Any, message: String) {
        verboseRoot(`object`.javaClass.simpleName, message)
    }

    /**
     * 记录“verbose”级别的信息

     * @param tag     the tag
     * *
     * @param message the message
     */
    fun verboseRoot(tag: String?, message: String) {
        var tag = tag
        if (isDebug) {
            tag = debugTag + (if (tag == null || tag.trim { it <= ' ' }.length == 0) "" else "-") + tag
            val msg = message + traceElement
            try {
                Log.v(tag, msg)
            } catch (e: Exception) {
                println(tag + ">>>" + msg)
            }

        }
    }

    /**
     * Debug.

     * @param message the message
     */
    fun debug(message: String) {
        debugRoot("", message)
    }

    /**
     * Debug.

     * @param object  the object
     * *
     * @param message the message
     */
    fun debug(`object`: Any, message: String) {
        debugRoot(`object`.javaClass.simpleName, message)
    }

    /**
     * 记录“debug”级别的信息

     * @param tag     the tag
     * *
     * @param message the message
     */
    fun debugRoot(tag: String?, message: String) {
        var tag = tag
        if (isDebug) {
            tag = debugTag + (if (tag == null || tag.trim { it <= ' ' }.length == 0) "" else "-") + tag
            val msg = message + traceElement
            try {
                Log.d(tag, msg)
            } catch (e: Exception) {
                println(tag + ">>>" + msg)
            }

        }
    }

    /**
     * Warn.

     * @param e the e
     */
    fun warn(e: Throwable) {
        warn(toStackTraceString(e))
    }

    /**
     * Warn.

     * @param message the message
     */
    fun warn(message: String) {
        warnRoot("", message)
    }

    /**
     * Warn.

     * @param object  the object
     * *
     * @param message the message
     */
    fun warn(`object`: Any, message: String) {
        warnRoot(`object`.javaClass.simpleName, message)
    }

    /**
     * Warn.

     * @param object the object
     * *
     * @param e      the e
     */
    fun warn(`object`: Any, e: Throwable) {
        warnRoot(`object`.javaClass.simpleName, toStackTraceString(e))
    }

    /**
     * 记录“warn”级别的信息

     * @param tag     the tag
     * *
     * @param message the message
     */
    fun warnRoot(tag: String?, message: String) {
        var tag = tag
        if (isDebug) {
            tag = debugTag + (if (tag == null || tag.trim { it <= ' ' }.length == 0) "" else "-") + tag
            val msg = message + traceElement
            try {
                Log.w(tag, msg)
            } catch (e: Exception) {
                println(tag + ">>>" + msg)
            }

        }
    }

    /**
     * Error.

     * @param e the e
     */
    fun error(e: Throwable) {
        error(toStackTraceString(e))
    }

    /**
     * Error.

     * @param message the message
     */
    fun error(message: String) {
        errorRoot("", message)
    }

    /**
     * Error.

     * @param object  the object
     * *
     * @param message the message
     */
    fun error(`object`: Any, message: String) {
        errorRoot(`object`.javaClass.simpleName, message)
    }

    /**
     * Error.

     * @param object the object
     * *
     * @param e      the e
     */
    fun error(`object`: Any, e: Throwable) {
        errorRoot(`object`.javaClass.simpleName, toStackTraceString(e))
    }

    /**
     * 记录“error”级别的信息

     * @param tag     the tag
     * *
     * @param message the message
     */
    fun errorRoot(tag: String?, message: String) {
        var tag = tag
        if (isDebug) {
            tag = debugTag + (if (tag == null || tag.trim { it <= ' ' }.length == 0) "" else "-") + tag
            val msg = message + traceElement
            try {
                Log.e(tag, msg)
            } catch (e: Exception) {
                System.err.println(tag + ">>>" + msg)
            }

        }
    }

    /**
     * 在某个方法中调用生成.trace文件。然后拿到电脑上用DDMS工具打开分析

     * @see .stopMethodTracing
     */
    fun startMethodTracing() {
        if (isDebug) {
            Debug.startMethodTracing(Environment.getExternalStorageDirectory().absolutePath + File.separator + debugTag + ".trace")
        }
    }

    /**
     * Stop method tracing.
     */
    fun stopMethodTracing() {
        if (isDebug) {
            Debug.stopMethodTracing()
        }
    }

    /**
     * To stack trace string string.
     *
     *
     * 此方法参见：https://github.com/Ereza/CustomActivityOnCrash

     * @param throwable the throwable
     * *
     * @return the string
     */
    fun toStackTraceString(throwable: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        throwable.printStackTrace(pw)
        var stackTraceString = sw.toString()
        //Reduce data to 128KB so we don't get a TransactionTooLargeException when sending the intent.
        //The limit is 1MB on Android but some devices seem to have it lower.
        //See: http://developer.android.com/reference/android/os/TransactionTooLargeException.html
        //And: http://stackoverflow.com/questions/11451393/what-to-do-on-transactiontoolargeexception#comment46697371_12809171
        if (stackTraceString.length > MAX_STACK_TRACE_SIZE) {
            val disclaimer = " [stack trace too large]"
            stackTraceString = stackTraceString.substring(0, MAX_STACK_TRACE_SIZE - disclaimer.length) + disclaimer
        }
        return stackTraceString
    }

    /**
     * 可显示调用方法所在的文件行号，在AndroidStudio的logcat处可点击定位。
     * 此方法参考：https://github.com/orhanobut/logger
     */
    private //corresponding method count with the current stack may exceeds the stack trace. Trims the count
    val traceElement: String
        get() {
            try {
                var methodCount = METHOD_COUNT
                val trace = Thread.currentThread().stackTrace
                val stackOffset = _getStackOffset(trace)
                if (methodCount + stackOffset > trace.size) {
                    methodCount = trace.size - stackOffset - 1
                }

                var level = "    "
                val builder = StringBuilder()
                for (i in methodCount downTo 1) {
                    val stackIndex = i + stackOffset
                    if (stackIndex >= trace.size) {
                        continue
                    }
                    builder.append("\n")
                            .append(level)
                            .append(_getSimpleClassName(trace[stackIndex].className))
                            .append(".")
                            .append(trace[stackIndex].methodName)
                            .append(" ")
                            .append("(")
                            .append(trace[stackIndex].fileName)
                            .append(":")
                            .append(trace[stackIndex].lineNumber)
                            .append(")")
                    level += "    "
                }
                return builder.toString()
            } catch (e: Exception) {
                return ""
            }

        }

    /**
     * Determines the starting index of the stack trace, after method calls made by this class.

     * @param trace the stack trace
     * *
     * @return the stack offset
     */
    private fun _getStackOffset(trace: Array<StackTraceElement>): Int {
        var i = MIN_STACK_OFFSET
        while (i < trace.size) {
            val e = trace[i]
            val name = e.className
            if (name != LogUtils::class.java.name) {
                return --i
            }
            i++
        }
        return -1
    }

    private fun _getSimpleClassName(name: String): String {
        val lastIndex = name.lastIndexOf(".")
        return name.substring(lastIndex + 1)
    }

}

package com.yinp.kotlinroomdemo

/**
 * @author   :yinpeng
 * date      :2021/10/12
 * package   :com.yinp.myapplication
 * describe  :
 */
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * 日志工具类
 */
object LogUtil {
    var DEBUG = true
    var DEFAULT_TAG = "DebugLog"

    /**
     * 仅打印日志
     */
    fun print(content: Any?) {
        if (DEBUG) {
            Log.d(DEFAULT_TAG, content.toString())
        }
    }

    /**
     * 输出错误日志
     */
    fun error(message: String, throwable: Throwable) {
        if (DEBUG) {
            Log.e(DEFAULT_TAG, message, throwable)
        }
    }

    /**
     * 保存日志
     * @param type 日志类型
     * @param code 日志代码
     * @param message 日志信息
     */
    fun saveLog(type: String, code: Int, message: String) {
        //异步执行的协程语句块
        GlobalScope.launch {
            DatabaseManager.db.logDao().getFirstLogTime()
                .collect {
                    Log.d("abcd", "saveLog: $it")
                }
            try {
                print("saveLog{$message}")
                DatabaseManager.db.logDao().save(LogEntity(type, code, message))
            } catch (exception: Exception) {
                error("Handle Exception in LogUtil.saveLog", exception)
            }
        }
    }

    fun query() {
        //会阻塞当前线程的协程语句块
        GlobalScope.launch {
            delay(1000)
            try {
                val logList = DatabaseManager.db.logDao().getLogList()
                println("query number = ${logList.size}")
                logList.map {
                    println("query = $it")
                    println("value+${it.message}")
                }
            } catch (exception: Exception) {
                println("query error = ${exception.message}")
                exception.printStackTrace()
            }
        }
    }
}

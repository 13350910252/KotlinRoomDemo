package com.yinp.kotlinroomdemo
import android.util.Log
import androidx.room.Dao
import androidx.room.Query
import com.yinp.myapplication.LogEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow

/**
 * @author   :yinpeng
 * date      :2021/10/12
 * package   :com.yinp.myapplication
 * describe  :
 */
@Dao
interface LogDao : BaseDao<LogEntity> {
    /**
     * 获取第一个日志的时间
     */
    @Query("select time from Mylo2g order by time asc limit 1")
    fun getFirstLogTime(): Flow<Long>

    fun getFirstLogTime2(): Flow<Long> = flow {
        emit(1010L)
    }

    fun getAllDistinctUntilChanged() = getFirstLogTime().distinctUntilChanged()

    /**
     * 获取最后一个日志的时间
     */
    @Query("select time from Mylo2g order by time desc limit 1")
    abstract suspend fun getLastLogTime(): Long

    /**
     * 根据时间筛选获取日志列表。
     */
    @Query("select * from Mylo2g where time>=:startTime and time <=:endTime")
    abstract fun getLogByFilter(startTime: Long, endTime: Long): List<LogEntity>

    suspend fun getLogList(startTime: Long = 0L, endTime: Long = 0L): List<LogEntity> {
        val list: List<LogEntity>
        val start = if (startTime == 0L) {
            var startTime2 = 0L
//            getFirstLogTime() to startTime2
            getFirstLogTime2().collect {

            }
            startTime2
        } else {
            startTime
        }
        Log.d("abcd", "getLogList:aaa ")
        val end = if (endTime == 0L) {
            getLastLogTime()
        } else {
            endTime
        }
        list = getLogByFilter(start, end)
        return list
    }
}
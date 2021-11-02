package com.yinp.kotlinroomdemo

/**
 * @author   :yinpeng
 * date      :2021/10/12
 * package   :com.yinp.myapplication
 * describe  :
 */
import androidx.room.Database
import androidx.room.RoomDatabase
import com.yinp.myapplication.LogEntity

@Database(version =1, exportSchema = false, entities = [LogEntity::class, MyBean::class])
abstract class LogDatabase : RoomDatabase() {
    abstract fun logDao(): LogDao

    abstract fun myBeanDao(): MyBeanDao
}

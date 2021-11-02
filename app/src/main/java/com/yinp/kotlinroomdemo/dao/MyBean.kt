package com.yinp.kotlinroomdemo

/**
 * @author   :yinpeng
 * date      :2021/10/12
 * package   :com.yinp.myapplication
 * describe  :
 */
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "myBean")
class MyBean() {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var time: Long = 0
    var type: String = ""
    var code: Int = 0
    var message: String = ""

    constructor(type: String, code: Int, message: String) : this() {
        time = System.currentTimeMillis()
        this.type = type
        this.code = code
        this.message = message
    }
}
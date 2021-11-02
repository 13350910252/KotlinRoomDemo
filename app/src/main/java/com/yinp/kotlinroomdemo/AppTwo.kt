package com.yinp.kotlinroomdemo
import android.content.Context
import android.view.View
import com.yinp.kotlinroomdemo.App

/**
 * @author   :yinpeng
 * date      :2021/10/13
 * package   :com.yinp.myapplication
 * describe  :
 */
class AppTwo : App() {
    companion object {
       lateinit var app: Context
    }
    //用于存放视图
    val viewMap = ArrayList<View>()
    override fun onCreate() {
        super.onCreate()
        app = applicationContext
    }
}
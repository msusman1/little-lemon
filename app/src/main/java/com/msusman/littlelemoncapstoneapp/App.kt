package com.msusman.littlelemoncapstoneapp

import android.app.Application
import android.content.Context

/**
 * Created by Muhammad Usman : msusman97@gmail.com on 5/8/2023.
 */
class App : Application() {


    companion object {
        lateinit var appPrefs: AppPrefs
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=this
        appPrefs = AppPrefs(this)
    }
}
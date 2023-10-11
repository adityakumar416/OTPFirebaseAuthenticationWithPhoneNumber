package com.example.otpverifysample

import android.app.Application
import com.facebook.FacebookSdk.sdkInitialize
import com.facebook.appevents.AppEventsLogger


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize the SDK before executing any other operations,
        sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}
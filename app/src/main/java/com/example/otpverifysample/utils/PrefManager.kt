package com.example.otpverifysample.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    private val PREF_NAME = "OTP_VERIFY"
    private val sharedPreferences: SharedPreferences
    val editor : SharedPreferences.Editor

    init {
            sharedPreferences =context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
        editor=sharedPreferences.edit()
    }




}
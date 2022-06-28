package com.example.testlistdogskonfio.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PrefManager @Inject constructor(@ApplicationContext private val context: Context) {
    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor

    private var PRIVATE_MODE = 0

    companion object {
        private const val PREF_NAME = "testlistdogskonfio"
        private const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    }

    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    var isFirstTimeLaunch: Boolean
        get() = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.commit()
        }
}
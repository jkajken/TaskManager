package com.jk.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Pref(private val context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences("unique_name", MODE_PRIVATE)

    fun isOnBoardingShown(): Boolean {
        return pref.getBoolean(BOARDING_SHOW, false)
    }

    fun saveShowBoarding(isShow: Boolean) {
        pref.edit().putBoolean(BOARDING_SHOW, isShow).apply()
    }

    fun saveName(name: String) {
        pref.edit().putString(NAME_PROFILE, name).apply()
    }

    fun getName(): String? {
        return pref.getString(NAME_PROFILE,"")
    }


    companion object {
        private const val BOARDING_SHOW = "boarding.show"
        private const val NAME_PROFILE = "profile.name"
    }
}
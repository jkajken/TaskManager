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

    fun saveAge(age: String) {
        pref.edit().putString(AGE_PROFILE, age).apply()
    }

    fun getAge(): String? {
        return pref.getString(AGE_PROFILE,"")
    }

    fun saveImage(image: String) {
        pref.edit().putString(IMAGE_PROFILE, image).apply()
    }

    fun getImage(): String? {
        return pref.getString(IMAGE_PROFILE,"")
    }





    companion object {
        private const val BOARDING_SHOW = "boarding.show"
        private const val NAME_PROFILE = "profile.name"
        private const val AGE_PROFILE = "profile.age"
        private const val IMAGE_PROFILE = "profile.age"

    }
}
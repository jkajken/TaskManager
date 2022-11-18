package com.jk.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Pref (private val context: Context){

    private val pref: SharedPreferences = context.getSharedPreferences("uniqe_name",MODE_PRIVATE)

    fun isOnBoardingShown():Boolean{
        return pref.getBoolean(BOARDING_SHOW,false)
    }
    fun saveShowBoarding(isShow:Boolean){
        pref.edit().putBoolean(BOARDING_SHOW,isShow).apply()
    }

    companion object{
        private const val BOARDING_SHOW = "boarding.show"
    }
}
package com.jk.taskmanager

import android.app.Application
import androidx.room.Room
import com.jk.taskmanager.data.local.dataBase.AppDataBase

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        dataBase =  Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }
    companion object {
        lateinit var dataBase: AppDataBase
    }
}
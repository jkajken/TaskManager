package com.jk.taskmanager.data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jk.taskmanager.data.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
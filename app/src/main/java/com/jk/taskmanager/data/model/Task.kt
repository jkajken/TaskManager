package com.jk.taskmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var title:String?=null,
    var description:String?=null,
): Serializable

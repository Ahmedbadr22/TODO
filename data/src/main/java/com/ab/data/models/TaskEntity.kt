package com.ab.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "task_table"
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false
)

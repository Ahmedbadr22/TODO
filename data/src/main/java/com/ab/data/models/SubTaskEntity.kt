package com.ab.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sub_task_table",
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["taskId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SubTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskId: Long,
    val title: String,
    val isCompleted: Boolean = false
)

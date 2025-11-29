package com.ab.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ab.data.dao.SubTaskDao
import com.ab.data.dao.TaskDao
import com.ab.data.models.SubTaskEntity
import com.ab.data.models.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
        SubTaskEntity::class
    ],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
    abstract fun getSubTaskDao(): SubTaskDao
}
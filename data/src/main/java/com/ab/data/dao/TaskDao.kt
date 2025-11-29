package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ab.data.models.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    suspend fun listAll(): List<TaskEntity>
    @Query("SELECT * FROM task_table WHERE id = :id")
    suspend fun getById(id: Int): TaskEntity?
    @Upsert
    suspend fun upsert(task: TaskEntity)
    @Delete
    suspend fun delete(task: TaskEntity)
}
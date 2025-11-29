package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ab.data.models.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    suspend fun listAll(): List<TaskEntity>

    @Query("SELECT * FROM task_table")
    fun listAllAsFlow(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task_table WHERE id = :id")
    suspend fun getById(id: Long): TaskEntity?

    @Upsert
    suspend fun upsert(task: TaskEntity): Long

    @Delete
    suspend fun delete(task: TaskEntity)
}
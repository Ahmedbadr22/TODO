package com.ab.data.datasource.task

import com.ab.data.models.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskLocalDataSource {
    suspend fun listAll(): List<TaskEntity>
    fun listAllAsFlow(): Flow<List<TaskEntity>>
    suspend fun getById(id: Long): TaskEntity?
    suspend fun insert(task: TaskEntity): Long
    suspend fun update(task: TaskEntity)
    suspend fun delete(task: TaskEntity)
}

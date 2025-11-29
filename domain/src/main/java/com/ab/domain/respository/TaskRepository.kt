package com.ab.domain.respository

import com.ab.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun listAll(): List<TaskModel>
    fun listAllAsFlow(): Flow<List<TaskModel>>
    suspend fun getById(id: Long): TaskModel?
    suspend fun add(task: TaskModel): Long
    suspend fun edit(task: TaskModel)
    suspend fun delete(task: TaskModel)
}
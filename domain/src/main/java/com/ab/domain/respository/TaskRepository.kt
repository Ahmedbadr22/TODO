package com.ab.domain.respository

import com.ab.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun listAll(): List<TaskModel>
    suspend fun listAllAsFlow(): Flow<List<TaskModel>>
    suspend fun getById(id: Int): TaskModel?
    suspend fun add(task: TaskModel)
    suspend fun edit(task: TaskModel)
    suspend fun delete(task: TaskModel)
}
package com.ab.data.datasource.task

import com.ab.data.models.TaskEntity

interface TaskLocalDataSource {
    suspend fun listAll(): List<TaskEntity>
    suspend fun getById(id: Int): TaskEntity?
    suspend fun insert(task: TaskEntity)
    suspend fun update(task: TaskEntity)
    suspend fun delete(task: TaskEntity)
}

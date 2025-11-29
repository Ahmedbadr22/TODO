package com.ab.data.datasource.subtask

import com.ab.data.models.SubTaskEntity

interface SubTaskLocalDataSource {
    suspend fun listAllByTaskId(taskId: Int): List<SubTaskEntity>
    suspend fun insert(subTask: SubTaskEntity)
    suspend fun update(subTask: SubTaskEntity)
    suspend fun delete(subTask: SubTaskEntity)
}
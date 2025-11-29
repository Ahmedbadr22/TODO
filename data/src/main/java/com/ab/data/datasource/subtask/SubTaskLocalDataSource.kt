package com.ab.data.datasource.subtask

import com.ab.data.models.SubTaskEntity
import kotlinx.coroutines.flow.Flow

interface SubTaskLocalDataSource {
    suspend fun listAllByTaskId(taskId: Int): List<SubTaskEntity>
    fun listAllByTaskIdAsFlow(taskId: Int): Flow<List<SubTaskEntity>>
    suspend fun insert(subTask: SubTaskEntity)
    suspend fun insert(subTasks: List<SubTaskEntity>)
    suspend fun update(subTask: SubTaskEntity)
    suspend fun delete(subTask: SubTaskEntity)
}
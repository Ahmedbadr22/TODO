package com.ab.domain.respository

import com.ab.domain.model.SubTaskModel
import kotlinx.coroutines.flow.Flow

interface SubTaskRepository {
    suspend fun listByTaskId(id: Long): List<SubTaskModel>
    fun listByTaskIdAsFlow(id: Long): Flow<List<SubTaskModel>>
    suspend fun add(task: SubTaskModel)
    suspend fun addList(tasks: List<SubTaskModel>)
    suspend fun edit(task: SubTaskModel)
    suspend fun delete(task: SubTaskModel)
}
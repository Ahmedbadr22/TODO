package com.ab.data.repository

import com.ab.data.datasource.subtask.SubTaskLocalDataSource
import com.ab.data.datasource.task.TaskLocalDataSource
import com.ab.data.mapper.task.toDomain
import com.ab.data.mapper.task.toDomainList
import com.ab.data.mapper.task.toEntitiesList
import com.ab.data.mapper.task.toEntity
import com.ab.data.models.SubTaskEntity
import com.ab.data.models.TaskEntity
import com.ab.domain.model.SubTaskModel
import com.ab.domain.model.TaskModel
import com.ab.domain.respository.SubTaskRepository
import com.ab.domain.respository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SubTaskRepositoryImpl @Inject constructor(
    private val subTaskLocalDataSource: SubTaskLocalDataSource,
) : SubTaskRepository {
    override suspend fun listByTaskId(id: Long): List<SubTaskModel> {
        return subTaskLocalDataSource
            .listAllByTaskId(id)
            .toDomainList()
    }

    override fun listByTaskIdAsFlow(id: Long): Flow<List<SubTaskModel>> {
        return subTaskLocalDataSource
            .listAllByTaskIdAsFlow(id)
            .map(List<SubTaskEntity>::toDomainList)
    }

    override suspend fun add(task: SubTaskModel) {
        val entity = task.toEntity()
        subTaskLocalDataSource.insert(entity)
    }

    override suspend fun addList(tasks: List<SubTaskModel>) {
        val entities = tasks.toEntitiesList()
        subTaskLocalDataSource.insert(entities)
    }

    override suspend fun edit(task: SubTaskModel) {
        val entity = task.toEntity()
        subTaskLocalDataSource.update(entity)
    }

    override suspend fun delete(task: SubTaskModel) {
        val entity = task.toEntity()
        subTaskLocalDataSource.delete(entity)
    }

}
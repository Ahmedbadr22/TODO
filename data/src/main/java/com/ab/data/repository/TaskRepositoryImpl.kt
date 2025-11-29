package com.ab.data.repository

import com.ab.data.datasource.task.TaskLocalDataSource
import com.ab.data.mapper.task.toDomain
import com.ab.data.mapper.task.toDomainList
import com.ab.data.mapper.task.toEntity
import com.ab.data.models.TaskEntity
import com.ab.domain.model.TaskModel
import com.ab.domain.respository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TaskRepositoryImpl @Inject constructor(
    private val taskLocalDataSource: TaskLocalDataSource,
) : TaskRepository {
    override suspend fun listAll(): List<TaskModel> {
        return taskLocalDataSource
            .listAll()
            .toDomainList()
    }

    override fun listAllAsFlow(): Flow<List<TaskModel>> {
        return taskLocalDataSource
            .listAllAsFlow()
            .map(List<TaskEntity>::toDomainList)
    }

    override suspend fun getById(id: Int): TaskModel? {
        return taskLocalDataSource
            .getById(id)
            ?.toDomain()
    }


    override suspend fun add(task: TaskModel): Long {
        val entity = task.toEntity()
        return taskLocalDataSource.insert(entity)
    }

    override suspend fun edit(task: TaskModel) {
        val entity = task.toEntity()
        taskLocalDataSource.update(entity)
    }

    override suspend fun delete(task: TaskModel) {
        val entity = task.toEntity()
        taskLocalDataSource.delete(entity)
    }
}
package com.ab.data.datasource.task

import com.ab.data.dao.TaskDao
import com.ab.data.models.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskLocalDataSourceImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskLocalDataSource {
    override suspend fun listAll(): List<TaskEntity> {
        return withContext(Dispatchers.IO) {
            taskDao.listAll()
        }
    }

    override fun listAllAsFlow(): Flow<List<TaskEntity>> = taskDao.listAllAsFlow()

    override suspend fun getById(id: Int): TaskEntity? {
        return withContext(Dispatchers.IO) {
            taskDao.getById(id)
        }
    }

    override suspend fun insert(task: TaskEntity) {
        withContext(Dispatchers.IO) {
            taskDao.upsert(task)
        }
    }

    override suspend fun update(task: TaskEntity) {
        withContext(Dispatchers.IO) {
            taskDao.upsert(task)
        }
    }

    override suspend fun delete(task: TaskEntity) {
        withContext(Dispatchers.IO) {
            taskDao.delete(task)
        }
    }
}

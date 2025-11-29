package com.ab.data.datasource.subtask

import com.ab.data.dao.SubTaskDao
import com.ab.data.models.SubTaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubTaskLocalDataSourceImpl @Inject constructor(
    private val subTaskDao: SubTaskDao
) : SubTaskLocalDataSource {
    override suspend fun listAllByTaskId(taskId: Int): List<SubTaskEntity> {
        return withContext(Dispatchers.IO) {
            subTaskDao.listAllByTaskId(taskId)
        }
    }

    override  fun listAllByTaskIdAsFlow(taskId: Int): Flow<List<SubTaskEntity>> =
        subTaskDao.listAllByTaskIdAsFlow(taskId)

    override suspend fun insert(subTask: SubTaskEntity) {
        withContext(Dispatchers.IO) {
            subTaskDao.upsert(subTask)
        }
    }

    override suspend fun update(subTask: SubTaskEntity) {
        withContext(Dispatchers.IO) {
            subTaskDao.upsert(subTask)
        }
    }

    override suspend fun delete(subTask: SubTaskEntity) {
        withContext(Dispatchers.IO) {
            subTaskDao.delete(subTask)
        }
    }
}
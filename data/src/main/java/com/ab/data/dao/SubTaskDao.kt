package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ab.data.models.SubTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubTaskDao {
    @Query("SELECT * FROM sub_task_table WHERE taskId = :taskId")
    suspend fun listAllByTaskId(taskId: Long): List<SubTaskEntity>

    @Query("SELECT * FROM sub_task_table WHERE taskId = :taskId")
    fun listAllByTaskIdAsFlow(taskId: Long): Flow<List<SubTaskEntity>>

    @Upsert
    suspend fun upsert(subTask: SubTaskEntity)

    @Upsert
    suspend fun upsert(subTasks: List<SubTaskEntity>)

    @Delete
    suspend fun delete(subTask: SubTaskEntity)
}
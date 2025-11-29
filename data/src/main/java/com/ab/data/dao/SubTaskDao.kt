package com.ab.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ab.data.models.SubTaskEntity

@Dao
interface SubTaskDao {
    @Query("SELECT * FROM sub_task_table WHERE taskId = :taskId")
    suspend fun listAllByTaskId(taskId: Int): List<SubTaskEntity>
    @Upsert
    suspend fun upsert(subTask: SubTaskEntity)
    @Delete
    suspend fun delete(subTask: SubTaskEntity)
}
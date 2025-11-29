package com.ab.domain.usecase.task

import com.ab.domain.model.TaskModel
import com.ab.domain.respository.SubTaskRepository
import com.ab.domain.respository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
    private val subTaskRepository: SubTaskRepository
) {
    suspend operator fun invoke(): Flow<List<TaskModel>> {
        return taskRepository
            .listAllAsFlow()
            .map { taskModels ->
                taskModels.map {
                    val subTasksCount = subTaskRepository.listByTaskId(it.id)
                    it.copy(subTasksCount = subTasksCount.size)
                }
            }
    }
}
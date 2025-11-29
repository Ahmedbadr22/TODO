package com.ab.data.mapper.task

import com.ab.data.models.TaskEntity
import com.ab.domain.model.TaskModel

fun TaskModel.toEntity(): TaskEntity = TaskEntity(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
)

fun TaskEntity.toDomain(): TaskModel = TaskModel(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
)

fun List<TaskEntity>.toDomainList(): List<TaskModel> = map(TaskEntity::toDomain)
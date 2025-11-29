package com.ab.data.mapper.task

import com.ab.data.models.SubTaskEntity
import com.ab.data.models.TaskEntity
import com.ab.domain.model.SubTaskModel

fun SubTaskModel.toEntity(): SubTaskEntity = SubTaskEntity(
    title = title,
    isCompleted = isCompleted,
    taskId = taskId
)

fun List<SubTaskModel>.toEntitiesList(): List<SubTaskEntity> = map(SubTaskModel::toEntity)

fun SubTaskEntity.toDomain(): SubTaskModel = SubTaskModel(
    id = id,
    title = title,
    isCompleted = isCompleted,
    taskId = taskId
)

fun List<SubTaskEntity>.toDomainList(): List<SubTaskModel> = map(SubTaskEntity::toDomain)
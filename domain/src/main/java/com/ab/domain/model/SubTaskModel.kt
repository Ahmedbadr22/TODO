package com.ab.domain.model

data class SubTaskModel(
    val id: Int = 0,
    val taskId: Long,
    val title: String,
    val isCompleted: Boolean = false
)

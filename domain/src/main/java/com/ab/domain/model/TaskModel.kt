package com.ab.domain.model


data class TaskModel(
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val subTasksCount: Int = 0
)

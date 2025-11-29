package com.ab.todo.navigation

import kotlinx.serialization.Serializable

@Serializable
data object TaskListRoute

@Serializable
data class TaskDetailRoute(val id: Int)

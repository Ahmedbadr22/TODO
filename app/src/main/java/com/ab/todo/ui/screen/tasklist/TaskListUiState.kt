package com.ab.todo.ui.screen.tasklist

import com.ab.domain.model.TaskModel

sealed class TaskListUiState {
    data class Content(val tasks: List<TaskModel>): TaskListUiState()
    data object Empty: TaskListUiState()
}

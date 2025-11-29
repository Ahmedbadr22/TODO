package com.ab.todo.ui.screen.tasklist

import com.ab.domain.model.TaskModel

sealed class TaskListUiEvent {
    data object NavigateToAddTask: TaskListUiEvent()
    data class DeleteTaskClick(val task: TaskModel): TaskListUiEvent()
    data class NavigateToTaskDetail(val id: Long): TaskListUiEvent()
}
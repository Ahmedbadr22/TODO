package com.ab.todo.ui.screen.tasklist

sealed class TaskListUiEvent {
    data object NavigateToAddTask: TaskListUiEvent()
}
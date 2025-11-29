package com.ab.todo.ui.screen.taskdetail

sealed class TaskDetailUiEffect {
    data object OnNavigateBack: TaskDetailUiEffect()
}
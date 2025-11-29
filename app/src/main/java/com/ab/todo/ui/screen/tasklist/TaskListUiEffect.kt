package com.ab.todo.ui.screen.tasklist

import com.ab.domain.model.SubTaskModel

sealed class TaskListUiEffect {
    data object OnNavigateBack: TaskListUiEffect()
}
package com.ab.todo.ui.screen.addtask

import com.ab.domain.model.SubTaskModel

sealed class AddTaskUiEffect {
    data object OnNavigateBack: AddTaskUiEffect()
    data object OnShowSuccessMessage: AddTaskUiEffect()
}
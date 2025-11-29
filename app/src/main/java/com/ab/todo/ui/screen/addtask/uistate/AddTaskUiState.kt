package com.ab.todo.ui.screen.addtask.uistate

import com.ab.domain.model.SubTaskModel

data class AddTaskUiState(
    val title: String = "",
    val description: String = "",
    val subTasks: List<SubTaskModel> = emptyList(),
)

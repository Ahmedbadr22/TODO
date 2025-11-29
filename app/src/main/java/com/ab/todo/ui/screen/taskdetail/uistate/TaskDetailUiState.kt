package com.ab.todo.ui.screen.taskdetail.uistate

import com.ab.domain.model.SubTaskModel

data class TaskDetailUiState(
    val title: String = "",
    val description: String = "",
    val subTasks: List<SubTaskModel> = emptyList(),
)

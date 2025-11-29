package com.ab.todo.ui.screen.taskdetail

import com.ab.domain.model.SubTaskModel

sealed class TaskDetailUiEvent {
    data class OnToggleSubTaskStatus(val subTask: SubTaskModel) : TaskDetailUiEvent()
    data object OnNavigateBackClick: TaskDetailUiEvent()
}
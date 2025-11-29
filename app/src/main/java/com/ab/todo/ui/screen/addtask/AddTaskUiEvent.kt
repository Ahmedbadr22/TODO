package com.ab.todo.ui.screen.addtask

import com.ab.domain.model.SubTaskModel

sealed class AddTaskUiEvent {
    data class OnTitleChange(val text: String): AddTaskUiEvent()
    data class OnDescriptionChange(val text: String): AddTaskUiEvent()
    data object OnAddSubTaskClick: AddTaskUiEvent()
    data object OnNavigateBackClick: AddTaskUiEvent()
    data object OnSaveClick: AddTaskUiEvent()
    data class OnSubTaskTitleChange(val id: Int, val text: String): AddTaskUiEvent()
    data class OnDeleteSubTaskClick(val subTask: SubTaskModel): AddTaskUiEvent()
}
package com.ab.todo.ui.screen.addtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ab.domain.model.SubTaskModel
import com.ab.domain.model.TaskModel
import com.ab.domain.respository.SubTaskRepository
import com.ab.domain.respository.TaskRepository
import com.ab.todo.ui.screen.addtask.uistate.AddTaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val subTaskRepository: SubTaskRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<AddTaskUiState> = MutableStateFlow(AddTaskUiState())
    val uiState: StateFlow<AddTaskUiState> = _uiState

    private val _effect: Channel<AddTaskUiEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: AddTaskUiEvent) {
        when (event) {
            AddTaskUiEvent.OnAddSubTaskClick -> onAddSubTaskClick()
            is AddTaskUiEvent.OnDescriptionChange -> {
                _uiState.update { state -> state.copy(description = event.text) }
            }

            is AddTaskUiEvent.OnTitleChange -> {
                _uiState.update { state -> state.copy(title = event.text) }
            }

            is AddTaskUiEvent.OnDeleteSubTaskClick -> onDeleteSubTaskClick(event.subTask)
            is AddTaskUiEvent.OnSubTaskTitleChange -> onSubTaskTitleChange(event.id, event.text)
            AddTaskUiEvent.OnSaveClick -> onSaveClick()
            AddTaskUiEvent.OnNavigateBackClick -> _effect.trySend(AddTaskUiEffect.OnNavigateBack)
        }
    }

    private fun onSaveClick() {
        val title = _uiState.value.title
        val description = _uiState.value.description

        if (title.isEmpty() || description.isEmpty()) return

        val taskModel = TaskModel(
            title = title,
            description = description,
        )

        viewModelScope.launch {
            val insertedTaskId = taskRepository.add(taskModel)
            val subTasks = _uiState.value.subTasks.map {
                it.copy(taskId = insertedTaskId)
            }

            subTaskRepository.addList(subTasks)
            _effect.trySend(AddTaskUiEffect.OnShowSuccessMessage)
            _uiState.update { AddTaskUiState() }
            delay(1500L)
            _effect.trySend(AddTaskUiEffect.OnNavigateBack)
        }
    }

    private fun onSubTaskTitleChange(id: Int, text: String) {
        _uiState.update { state ->
            val task = state.subTasks.find { it.id == id } ?: return
            val filteredTasks = state.subTasks.filter { it.id != id }
            val newTasks = filteredTasks + task.copy(title = text)
            state.copy(subTasks = newTasks)
        }
    }

    private fun onDeleteSubTaskClick(subTask: SubTaskModel) {
        _uiState.update { state ->
            state.copy(
                subTasks = state.subTasks.filter { it.id != subTask.id }
            )
        }
    }

    private fun onAddSubTaskClick() {
        val isAnyTaskEmpty = _uiState.value.subTasks.any { it.title.isEmpty() }
        if (isAnyTaskEmpty) return

        _uiState.update { state ->
            val emptySubTask = SubTaskModel(
                id = state.subTasks.size + 1,
                title = "",
                taskId = 0
            )

            state.copy(subTasks = state.subTasks + emptySubTask)
        }
    }
}
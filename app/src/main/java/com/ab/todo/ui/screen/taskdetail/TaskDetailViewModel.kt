package com.ab.todo.ui.screen.taskdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ab.domain.model.SubTaskModel
import com.ab.domain.respository.SubTaskRepository
import com.ab.domain.respository.TaskRepository
import com.ab.todo.navigation.TaskDetailRoute
import com.ab.todo.ui.screen.taskdetail.uistate.TaskDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val subTaskRepository: SubTaskRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val route = savedStateHandle.toRoute<TaskDetailRoute>()
    private val _uiState: MutableStateFlow<TaskDetailUiState> = MutableStateFlow(TaskDetailUiState())
    val uiState: StateFlow<TaskDetailUiState> = _uiState

    private val _effect: Channel<TaskDetailUiEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            val task = taskRepository.getById(route.id)
            if(task != null) {
                _uiState.update { state ->
                    state.copy(
                        title = task.title,
                        description = task.description.orEmpty(),
                    )
                }

                subTaskRepository
                    .listByTaskIdAsFlow(route.id)
                    .collectLatest { subTaskModels ->
                        _uiState.update { state ->
                            state.copy(
                                subTasks = subTaskModels
                            )
                        }
                    }
                return@launch
            }

            _effect.trySend(TaskDetailUiEffect.OnNavigateBack)
        }
    }

    fun onEvent(event: TaskDetailUiEvent) {
        when(event) {
            is TaskDetailUiEvent.OnToggleSubTaskStatus -> onUpdateSubTaskStatus(event.subTask)
            TaskDetailUiEvent.OnNavigateBackClick -> onNavigateBackClick()
        }
    }

    private fun onNavigateBackClick() {
        _effect.trySend(TaskDetailUiEffect.OnNavigateBack)
    }

    private fun onUpdateSubTaskStatus(subTask: SubTaskModel) {
        val updatedSubTask = subTask.copy(isCompleted = !subTask.isCompleted)
        viewModelScope.launch {
            subTaskRepository.edit(updatedSubTask)
        }
    }
}
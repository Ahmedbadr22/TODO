package com.ab.todo.ui.screen.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ab.domain.model.TaskModel
import com.ab.domain.respository.TaskRepository
import com.ab.domain.usecase.task.ListTasksUseCase
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
class TaskListViewModel @Inject constructor(
    private val listTasksUseCase: ListTasksUseCase,
    private val taskRepository: TaskRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<TaskListUiState> = MutableStateFlow(TaskListUiState.Empty)
    val uiState: StateFlow<TaskListUiState> = _uiState

    private val _effect: Channel<TaskListUiEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            listTasksUseCase()
                .collectLatest { tasks ->
                    when {
                        tasks.isEmpty() -> _uiState.update { TaskListUiState.Empty }
                        else -> _uiState.update { TaskListUiState.Content(tasks) }
                    }
                }
        }
    }

    fun onEvent(event: TaskListUiEvent) {
        when (event) {
            TaskListUiEvent.NavigateToAddTask -> _effect.trySend(TaskListUiEffect.OnNavigateToAddTask)
            is TaskListUiEvent.DeleteTaskClick -> onDeleteTaskClick(event.task)
            is TaskListUiEvent.NavigateToTaskDetail -> _effect.trySend(TaskListUiEffect.OnNavigateToTaskDetail(event.id))

        }
    }

    fun onDeleteTaskClick(task: TaskModel) {
        viewModelScope.launch {
            taskRepository.delete(task)
        }
    }

}
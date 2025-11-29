package com.ab.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ab.todo.ui.screen.addtask.AddTaskScreen
import com.ab.todo.ui.screen.addtask.AddTaskViewModel
import com.ab.todo.ui.screen.taskdetail.TaskDetailScreen
import com.ab.todo.ui.screen.taskdetail.TaskDetailViewModel
import com.ab.todo.ui.screen.tasklist.TaskListScreen
import com.ab.todo.ui.screen.tasklist.TaskListViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = TaskListRoute,
    ) {
        composable<TaskListRoute> {
            val viewModel = hiltViewModel<TaskListViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            TaskListScreen(
                uiState = uiState,
                onEvent = viewModel::onEvent,
                uiEffect = viewModel.effect,
                onNavigateToAddTask = {
                    navController.navigate(AddTaskRoute)
                },
                onNavigateToTaskDetail = {
                    navController.navigate(TaskDetailRoute(it))
                }
            )
        }

        composable<AddTaskRoute> {
            val viewModel = hiltViewModel<AddTaskViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            AddTaskScreen(
                uiState = uiState,
                onEvent = viewModel::onEvent,
                uiEffect = viewModel.effect,
                onNavigateBack = navController::navigateUp
            )
        }

        composable<TaskDetailRoute> {
            val viewModel = hiltViewModel<TaskDetailViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            TaskDetailScreen(
                uiState = uiState,
                onEvent = viewModel::onEvent,
                uiEffect = viewModel.effect,
                onNavigateBack = navController::navigateUp
            )
        }
    }
}
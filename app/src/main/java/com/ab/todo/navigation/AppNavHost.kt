package com.ab.todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ab.todo.ui.screen.tasklist.TaskListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = TaskListRoute,
    ) {
        composable<TaskListRoute> {
            TaskListScreen()
        }

        composable<TaskDetailRoute> {

        }
    }
}
package com.ab.todo.ui.screen.taskdetail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ab.todo.R
import com.ab.todo.ui.screen.taskdetail.uistate.TaskDetailUiState
import com.ab.todo.ui.theme.TODOTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    onEvent: (TaskDetailUiEvent) -> Unit,
    uiEffect: Flow<TaskDetailUiEffect>,
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(uiEffect) {
        uiEffect.collectLatest { effect ->
            when (effect) {
                TaskDetailUiEffect.OnNavigateBack -> onNavigateBack()
            }
        }
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.add_task))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(TaskDetailUiEvent.OnNavigateBackClick)
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.title,
                style = MaterialTheme.typography.displayLarge,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.description,
                style = MaterialTheme.typography.displaySmall,
            )
            HorizontalDivider()
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            Text(
                text = stringResource(R.string.sub_tasks),
                style = MaterialTheme.typography.titleLarge
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.subTasks) { subTask ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f))
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = subTask.title,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Checkbox(
                            checked = subTask.isCompleted,
                            onCheckedChange = {
                                onEvent(TaskDetailUiEvent.OnToggleSubTaskStatus(subTask))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(locale = "ar")
@Preview(locale = "ar", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AddTaskScreenPreview() {
    TODOTheme {
        TaskDetailScreen(
            uiState = TaskDetailUiState(),
            onEvent = {},
            onNavigateBack = {},
            uiEffect = emptyFlow()
        )
    }
}
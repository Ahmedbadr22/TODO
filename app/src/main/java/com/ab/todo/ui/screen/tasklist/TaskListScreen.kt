package com.ab.todo.ui.screen.tasklist

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ab.domain.model.TaskModel
import com.ab.todo.R
import com.ab.todo.ui.theme.TODOTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    uiState: TaskListUiState,
    onEvent: (TaskListUiEvent) -> Unit,
    effect: Flow<TaskListUiEffect>,
    onNavigateBack: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(stringResource(R.string.app_name))
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(TaskListUiEvent.NavigateToAddTask)
                        }
                    ) {
                        Icon(
                            Icons.Outlined.Edit,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        when(uiState) {
            is TaskListUiState.Content -> {
                TaskList(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxSize()
                        .padding(innerPadding),
                    tasks = uiState.tasks
                )
            }
            TaskListUiState.Empty -> EmptyData()
        }

    }
}

@Composable
fun TaskList(
    modifier: Modifier,
    tasks: List<TaskModel>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(tasks, key = { it.id }) { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        modifier = Modifier,
                        text = task.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    task.description?.let {
                        Text(
                            modifier = Modifier,
                            text = it,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    if (task.subTasksCount > 0) {
                        Text(
                            modifier = Modifier,
                            text = "${task.subTasksCount} Sub Tasks",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                IconButton(
                    onClick = {

                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyData(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(128.dp),
            painter = painterResource(R.drawable.ic_empty),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.no_data_found),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(locale = "ar")
@Preview(locale = "ar", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun TaskListScreenPreview() {
    TODOTheme {
        TaskListScreen(
            uiState = TaskListUiState.Empty,
            onEvent = {},
            effect = emptyFlow(),
            onNavigateBack = {}
        )
    }
}
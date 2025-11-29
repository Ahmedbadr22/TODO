package com.ab.todo.ui.screen.addtask

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ab.todo.R
import com.ab.todo.ui.screen.addtask.uistate.AddTaskUiState
import com.ab.todo.ui.theme.TODOTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    uiState: AddTaskUiState,
    onEvent: (AddTaskUiEvent) -> Unit,
    uiEffect: Flow<AddTaskUiEffect>,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(uiEffect) {
        uiEffect.collectLatest { effect ->
            when (effect) {
                AddTaskUiEffect.OnNavigateBack -> onNavigateBack()
                AddTaskUiEffect.OnShowSuccessMessage -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.added_successfully),
                        Toast.LENGTH_SHORT
                    ).show()
                }
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
                            onEvent(AddTaskUiEvent.OnNavigateBackClick)
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
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    )
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        onEvent(AddTaskUiEvent.OnSaveClick)
                    }
                ) {
                    Text(stringResource(R.string.save))
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            TodoTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = uiState.title,
                placeholder = stringResource(R.string.title),
                textStyle = MaterialTheme.typography.displayLarge,
                onValueChange = { text ->
                    onEvent(AddTaskUiEvent.OnTitleChange(text))
                }
            )
            TodoTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = uiState.description,
                placeholder = stringResource(R.string.description),
                onValueChange = { text ->
                    onEvent(AddTaskUiEvent.OnDescriptionChange(text))
                }
            )
            HorizontalDivider()
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.sub_tasks),
                    style = MaterialTheme.typography.titleLarge
                )
                TextButton(
                    onClick = {
                        onEvent(AddTaskUiEvent.OnAddSubTaskClick)
                    }
                ) {
                    Icon(
                        Icons.Outlined.Add,
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(R.string.add),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
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
                        TodoTextField(
                            modifier = Modifier.weight(1f),
                            text = subTask.title,
                            onValueChange = { text ->
                                onEvent(AddTaskUiEvent.OnSubTaskTitleChange(subTask.id, text))
                            },
                            placeholder = stringResource(R.string.enter_title),
                            textStyle = MaterialTheme.typography.titleMedium
                        )
                        IconButton(
                            onClick = {
                                onEvent(AddTaskUiEvent.OnDeleteSubTaskClick(subTask))
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
    }
}

@Composable
fun TodoTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    textStyle: TextStyle = MaterialTheme.typography.displaySmall
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        maxLines = 2,
        textStyle = textStyle
            .copy(color = MaterialTheme.colorScheme.onBackground),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                if (text.isEmpty() && !placeholder.isNullOrEmpty()) {
                    Text(
                        text = placeholder,
                        style = textStyle,
                        color = Color.Gray
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(locale = "ar")
@Preview(locale = "ar", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AddTaskScreenPreview() {
    TODOTheme {
        AddTaskScreen(
            uiState = AddTaskUiState(),
            onEvent = {},
            onNavigateBack = {},
            uiEffect = emptyFlow()
        )
    }
}
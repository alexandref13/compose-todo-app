@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.ale.composefirstapp.ui.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.ale.composefirstapp.model.TaskEntry

@Composable
fun TodoListScreen(todoListViewModel: TodoListViewModel = viewModel()) {
    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background,
        ), title = {
            Text(
                "TODO APP - Jetpack Compose", style = MaterialTheme.typography.headlineSmall
            )
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            todoListViewModel.addTask(context)
        }) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
        ) {
            TextInput(todoListViewModel)
            Content(todoListViewModel)
        }
    }
}

@Composable
fun TextInput(todoListViewModel: TodoListViewModel) {
    OutlinedTextField(
        value = todoListViewModel.taskText.value,
        onValueChange = { todoListViewModel.taskText.value = it },
        label = { Text("Adicione sua tarefa") },
        maxLines = 2,
        textStyle = MaterialTheme.typography.bodySmall,

        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Content(todoListViewModel: TodoListViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(todoListViewModel.tasks) { task ->
                TaskItem(task, todoListViewModel)
            }
        }
    }
}

@Composable
fun TaskItem(task: TaskEntry, todoListViewModel: TodoListViewModel) {
    val textStyle = if (task.isDone) TextStyle(
        color = Color(0xff1DB863), fontSize = 12.sp
    ) else TextStyle(color = Color(0xFFCFCFCF), fontSize = 12.sp)

    val background = if (task.isDone) Color.White else Color(0xFF7C7C7C)
    val checkBoxBackground =
        if (task.isDone) Color(0xff1DB863) else Color.White

    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = background,
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(color = checkBoxBackground, shape = RoundedCornerShape(4.dp))
                        .clickable {
                            todoListViewModel.changeIsDone(task)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (task.isDone)
                        Icon(Icons.Default.Check, contentDescription = "Check Icon", tint = Color.White)
                }
                Spacer(Modifier.width(16.dp))
                Text(task.name, modifier = Modifier.padding(8.dp), style = textStyle)
            }
            IconButton(onClick = {
                todoListViewModel.deleteATask(task)
            }) {
                Icon(
                    Icons.Default.Delete, contentDescription = "Delete", tint = Color(0xffB2B2B2),
                )
            }
        }
    }
}

@Composable
fun CountContainer(count: Int) {
    Card {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(count.toString(), modifier = Modifier.padding(8.dp))

        }
    }
}

@Preview
@Composable
fun PreviewMain() {
    TodoListScreen()
}
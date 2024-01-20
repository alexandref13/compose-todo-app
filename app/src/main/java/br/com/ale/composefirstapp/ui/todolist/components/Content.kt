package br.com.ale.composefirstapp.ui.todolist.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.ale.composefirstapp.ui.todolist.TodoListViewModel

@Composable
fun Content(todoListViewModel: TodoListViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(todoListViewModel.tasks) { task ->
                TaskItem(task, todoListViewModel)
            }
        }
    }
}
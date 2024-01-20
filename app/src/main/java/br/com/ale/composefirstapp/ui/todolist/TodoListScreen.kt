@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.ale.composefirstapp.ui.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.ale.composefirstapp.ui.theme.whiteColor
import br.com.ale.composefirstapp.ui.todolist.components.Content
import br.com.ale.composefirstapp.ui.todolist.components.Header
import br.com.ale.composefirstapp.ui.todolist.components.TextInputField

@Composable
fun TodoListScreen(todoListViewModel: TodoListViewModel = viewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(whiteColor),
        verticalArrangement = Arrangement.Center,
    ) {
        Box {
            Header(todoListViewModel = todoListViewModel)
            Box(
                modifier = Modifier.padding(vertical = 90.dp)
            ) {
                Column {
                    Box(modifier = Modifier.padding(horizontal = 24.dp)) {
                        TextInputField(todoListViewModel)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Content(todoListViewModel)
                }

            }
        }
    }
}


@Preview
@Composable
fun PreviewMain() {
    TodoListScreen()
}
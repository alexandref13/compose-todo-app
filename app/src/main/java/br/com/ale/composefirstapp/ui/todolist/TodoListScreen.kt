@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.ale.composefirstapp.ui.todolist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.ale.composefirstapp.R
import br.com.ale.composefirstapp.model.TaskEntry
import br.com.ale.composefirstapp.ui.theme.grayColor
import br.com.ale.composefirstapp.ui.theme.primaryLight
import br.com.ale.composefirstapp.ui.theme.successLight
import br.com.ale.composefirstapp.ui.theme.whiteColor

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

@Composable
fun Header(todoListViewModel: TodoListViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(primaryLight),
    ) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_image),
                    modifier = Modifier
                        .width(64.dp)
                        .height(26.dp),
                    contentDescription = "Logo TODO"
                )
                Row {
                    Text(
                        "Você tem",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = whiteColor
                        ),
                    )
                    Text(
                        " ${todoListViewModel.tasks.size} tarefas", style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = whiteColor
                        )
                    )
                }
            }
        }

    }
}

@Composable
fun TextInputField(todoListViewModel: TodoListViewModel) {
    // TODO -> Ajustar TextInputField

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = Color.White, shape = RoundedCornerShape(
                    topEnd = 5.dp, topStart = 5.dp, bottomEnd = 5.dp, bottomStart = 5.dp
                )
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                BasicTextField(
                    value = todoListViewModel.taskText.value,
                    onValueChange = { todoListViewModel.taskText.value = it },
                    maxLines = 2,
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = grayColor,
                    ),
                )
            }
            Box(modifier = Modifier.clickable {
                todoListViewModel.addTask(context)
            }) {
                Row {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(Color(0xFFEBEBEB))
                    ) {}
                    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            modifier = Modifier
                                .width(64.dp)
                                .height(26.dp),
                            contentDescription = "Arrow Right",
                            tint = grayColor
                        )
                    }
                }
            }
        }
    }


}

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

@Composable
fun TaskItem(task: TaskEntry, todoListViewModel: TodoListViewModel) {
    val textStyle = if (task.isDone) TextStyle(
        color = successLight, fontSize = 14.sp, textDecoration = TextDecoration.LineThrough,
    ) else TextStyle(color = Color(0xFF666666), fontSize = 14.sp)

    val background = if (task.isDone) Color.White else Color(0xFFe3e3e3)
    val checkBoxBackground = if (task.isDone) successLight else Color.White

    Box(
        modifier = Modifier
            .padding()
            .background(background)
            .height(64.dp),
        contentAlignment = Alignment.Center
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
                        .size(16.dp)
                        .background(color = checkBoxBackground, shape = RoundedCornerShape(4.dp))
                        .clickable {
                            todoListViewModel.changeIsDone(task)
                        }, contentAlignment = Alignment.Center
                ) {
                    if (task.isDone) Icon(
                        Icons.Default.Check,
                        contentDescription = "Check Icon",
                        tint = Color.White,
                        modifier = Modifier.size(8.dp),
                    )
                }
                Spacer(Modifier.width(16.dp))
                Text(task.name, modifier = Modifier.padding(8.dp), style = textStyle)
            }
            IconButton(onClick = {
                todoListViewModel.deleteATask(task)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Trash Icon",
                    tint = grayColor
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMain() {
    TodoListScreen()
}
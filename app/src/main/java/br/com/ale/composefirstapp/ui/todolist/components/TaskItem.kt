package br.com.ale.composefirstapp.ui.todolist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ale.composefirstapp.R
import br.com.ale.composefirstapp.model.TaskEntry
import br.com.ale.composefirstapp.ui.theme.grayColor
import br.com.ale.composefirstapp.ui.theme.successLight
import br.com.ale.composefirstapp.ui.todolist.TodoListViewModel

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

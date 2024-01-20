package br.com.ale.composefirstapp.ui.todolist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ale.composefirstapp.R
import br.com.ale.composefirstapp.ui.theme.grayColor
import br.com.ale.composefirstapp.ui.todolist.TodoListViewModel

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
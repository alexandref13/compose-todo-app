package br.com.ale.composefirstapp.ui.todolist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ale.composefirstapp.R
import br.com.ale.composefirstapp.ui.theme.primaryLight
import br.com.ale.composefirstapp.ui.theme.whiteColor
import br.com.ale.composefirstapp.ui.todolist.TodoListViewModel

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
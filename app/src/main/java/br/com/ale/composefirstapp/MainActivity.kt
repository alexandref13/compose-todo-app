package br.com.ale.composefirstapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ale.composefirstapp.ui.theme.ComposeFirstAppTheme
import androidx.compose.material3.TopAppBar as TopAppBar1

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFirstAppTheme {
                // A surface container using the 'background' color from the theme
                Main()
            }
        }
    }
}

data class Task(
    var name: String, var isDone: Boolean
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Main() {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var tasks by remember { mutableStateOf(emptyList<Task>()) }
    var text by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar1(colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background,
        ), title = {
            Text(
                "TODO APP - Jetpack Compose", style = MaterialTheme.typography.headlineSmall
            )
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            if(text.trim().isEmpty()){
                Toast.makeText(context, "Campo de tarefa é obrigatório", Toast.LENGTH_SHORT).show()
                return@FloatingActionButton
            }

            tasks += Task(text, false)

            text = ""
            focusManager.clearFocus()

            Log.i("TasksList", tasks.size.toString())
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
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Adicione sua tarefa") },
                maxLines = 2,
                textStyle = MaterialTheme.typography.bodySmall,

                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            Content(tasks)
        }
    }
}

@Composable
fun Content(tasks: List<Task>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = task.isDone, onCheckedChange = {
                    task.isDone = !task.isDone
                })
                Text(task.name, modifier = Modifier.padding(8.dp))
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red,
                )
            }
        }
    }
}

@Composable
fun CountContainer(count: Int) {
    Card() {
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
    Main()
}
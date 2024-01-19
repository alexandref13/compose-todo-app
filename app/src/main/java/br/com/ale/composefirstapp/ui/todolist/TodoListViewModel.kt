package br.com.ale.composefirstapp.ui.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import br.com.ale.composefirstapp.model.TaskEntry
import java.util.UUID

class TodoListViewModel : ViewModel() {
    @SuppressLint("MutableCollectionMutableState")
    private val _tasks = mutableStateListOf<TaskEntry>()
    val tasks: List<TaskEntry> = _tasks

    private val _taskText = mutableStateOf<String>("")
    val taskText: MutableState<String> = _taskText

    val isChecked = mutableStateOf(false)

    fun addTask(context: Context) {
        if (taskText.value.trim().isEmpty()) {
            Toast.makeText(context, "Campo de tarefa é obrigatório", Toast.LENGTH_SHORT).show()
            return
        }

        _tasks.add(TaskEntry(id = UUID.randomUUID(), name = taskText.value, isDone = false))

        taskText.value = ""

        Log.i("TasksList", tasks.size.toString())
    }

    fun deleteATask(task: TaskEntry){
       _tasks.remove(task)
    }

    fun changeIsDone(task: TaskEntry){
        val index = _tasks.indexOf(task)

        _tasks[index] = TaskEntry(id= task.id, name=task.name, isDone = !task.isDone)
    }
}
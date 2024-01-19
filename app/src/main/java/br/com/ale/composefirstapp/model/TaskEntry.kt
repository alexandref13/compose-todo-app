package br.com.ale.composefirstapp.model

import java.util.UUID

data class TaskEntry(
    var id: UUID, var name: String, var isDone: Boolean
)
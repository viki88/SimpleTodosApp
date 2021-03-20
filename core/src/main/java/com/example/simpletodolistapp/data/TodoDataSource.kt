package com.example.simpletodolistapp.data

import com.example.simpletodolistapp.domain.Todo

interface TodoDataSource {
    suspend fun addTodo(todo: Todo)
    suspend fun deleteTodo(todo :Todo)
    suspend fun updateTodo(todo :Todo)
    suspend fun readTodos() : List<Todo>
}
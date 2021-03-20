package com.example.simpletodolistapp.data

import com.example.simpletodolistapp.domain.Todo

class Repository (private var todoDataSource: TodoDataSource) {

    suspend fun addTodo(todo: Todo) = todoDataSource.addTodo(todo)

    suspend fun deleteTodo(todo: Todo) = todoDataSource.deleteTodo(todo)

    suspend fun updateTodo(todo: Todo) = todoDataSource.updateTodo(todo)

    suspend fun getAllTodos() :List<Todo> = todoDataSource.readTodos()

}
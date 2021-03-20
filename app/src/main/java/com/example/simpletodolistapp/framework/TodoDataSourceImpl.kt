package com.example.simpletodolistapp.framework

import com.example.simpletodolistapp.data.TodoDataSource
import com.example.simpletodolistapp.domain.Todo
import com.example.simpletodolistapp.framework.db.dao.TodoDAO
import com.example.simpletodolistapp.framework.db.entity.TodoEntity

class TodoDataSourceImpl (var todoDAO: TodoDAO) :TodoDataSource {

    override suspend fun addTodo(todo: Todo) {
        todoDAO.addTodo(TodoEntity(title = todo.title, todo = todo.todo))
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDAO.removeTodo(TodoEntity(todo.id, todo.title, todo.todo))
    }

    override suspend fun updateTodo(todo: Todo) {
        todoDAO.updateTodo(TodoEntity(todo.id, todo.title, todo.todo))
    }

    override suspend fun readTodos(): List<Todo> {
        val todos = arrayListOf<Todo>()

        val todoEntities = todoDAO.getTodos()
        for (todoEntity in todoEntities){
            todos.add(Todo(todoEntity.uid, todoEntity.title, todoEntity.todo))
        }

        return todos
    }
}
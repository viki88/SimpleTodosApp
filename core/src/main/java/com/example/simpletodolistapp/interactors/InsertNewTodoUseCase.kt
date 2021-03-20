package com.example.simpletodolistapp.interactors

import com.example.simpletodolistapp.data.Repository
import com.example.simpletodolistapp.domain.Todo

class InsertNewTodoUseCase(private val repository: Repository) {
    suspend operator fun invoke(todo :Todo){
        repository.addTodo(todo)
    }
}
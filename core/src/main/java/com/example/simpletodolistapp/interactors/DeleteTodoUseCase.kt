package com.example.simpletodolistapp.interactors

import com.example.simpletodolistapp.data.Repository
import com.example.simpletodolistapp.domain.Todo

class DeleteTodoUseCase(private var repository: Repository) {
    suspend operator fun invoke(todo: Todo){
        repository.deleteTodo(todo)
    }
}
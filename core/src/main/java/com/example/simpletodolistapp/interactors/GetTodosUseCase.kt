package com.example.simpletodolistapp.interactors

import com.example.simpletodolistapp.data.Repository
import com.example.simpletodolistapp.domain.Todo

class GetTodosUseCase(private val repository: Repository) {
    suspend operator fun invoke() :List<Todo>{
        return repository.getAllTodos()
    }
}
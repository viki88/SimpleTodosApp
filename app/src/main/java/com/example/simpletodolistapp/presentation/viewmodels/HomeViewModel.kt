package com.example.simpletodolistapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpletodolistapp.data.Repository
import com.example.simpletodolistapp.domain.Todo
import com.example.simpletodolistapp.interactors.DeleteTodoUseCase
import com.example.simpletodolistapp.interactors.GetTodosUseCase
import com.example.simpletodolistapp.interactors.InsertNewTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: Repository) :ViewModel(){

    var todosLiveData = MutableLiveData<List<Todo>>()

    private var insertNewTodoUseCase = InsertNewTodoUseCase(repository)
    private var getTodosUseCase = GetTodosUseCase(repository)
    private var deleteTodoUseCase = DeleteTodoUseCase(repository)

    fun loadTodos(){
        GlobalScope.launch {
            todosLiveData.postValue(getTodosUseCase.invoke())
        }
    }

    fun addTodos(todo: Todo){
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                insertNewTodoUseCase.invoke(todo)
            }
            todosLiveData.postValue(getTodosUseCase.invoke())
        }
    }

    fun deleteTodo(todo: Todo){
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                deleteTodoUseCase.invoke(todo)
            }
            todosLiveData.postValue(getTodosUseCase.invoke())
        }
    }

}
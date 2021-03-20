package com.example.simpletodolistapp.presentation.ui.home

import com.example.simpletodolistapp.domain.Todo

interface TodoListItemListener {
    fun onLongClick(todo: Todo)
    fun onClick(todo: Todo)
}
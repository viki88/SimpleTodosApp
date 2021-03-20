package com.example.simpletodolistapp.framework.db.dao

import androidx.room.*
import com.example.simpletodolistapp.framework.db.entity.TodoEntity

@Dao
interface TodoDAO {

    @Insert
    suspend fun addTodo(todoEntity: TodoEntity)

    @Delete
    suspend fun removeTodo(todoEntity: TodoEntity)

    @Query("SELECT * FROM TodoEntity")
    suspend fun getTodos() : List<TodoEntity>

    @Update
    suspend fun updateTodo(todoEntity: TodoEntity)

}
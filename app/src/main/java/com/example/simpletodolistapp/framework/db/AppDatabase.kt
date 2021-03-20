package com.example.simpletodolistapp.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpletodolistapp.framework.db.dao.TodoDAO
import com.example.simpletodolistapp.framework.db.entity.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun todoDao() :TodoDAO
}
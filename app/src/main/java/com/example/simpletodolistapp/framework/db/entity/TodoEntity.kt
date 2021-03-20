package com.example.simpletodolistapp.framework.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val uid :Long = 0,
    @ColumnInfo(name = "title") var title :String,
    @ColumnInfo(name = "todo") var todo :String
)
package com.example.simpletodolistapp.framework.di.module

import android.content.Context
import androidx.room.Room
import com.example.simpletodolistapp.data.Repository
import com.example.simpletodolistapp.data.TodoDataSource
import com.example.simpletodolistapp.framework.TodoDataSourceImpl
import com.example.simpletodolistapp.framework.db.AppDatabase
import com.example.simpletodolistapp.framework.db.dao.TodoDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDao(@ApplicationContext context: Context) :TodoDAO{
        return Room.databaseBuilder(context, AppDatabase::class.java, "todo_database").build().todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoDatasource(todoDAO: TodoDAO) :TodoDataSource{
        return TodoDataSourceImpl(todoDAO)
    }

    @Provides
    @Singleton
    fun provideRepository(todoDataSource: TodoDataSource) :Repository{
        return Repository(todoDataSource)
    }

}
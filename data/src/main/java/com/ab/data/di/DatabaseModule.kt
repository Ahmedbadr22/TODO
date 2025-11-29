package com.ab.data.di

import android.content.Context
import androidx.room.Room
import com.ab.data.dao.SubTaskDao
import com.ab.data.dao.TaskDao
import com.ab.data.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(db: TodoDatabase): TaskDao = db.getTaskDao()

    @Provides
    @Singleton
    fun provideSubTaskDao(db: TodoDatabase): SubTaskDao = db.getSubTaskDao()
}
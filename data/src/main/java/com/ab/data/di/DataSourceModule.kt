package com.ab.data.di

import com.ab.data.datasource.subtask.SubTaskLocalDataSource
import com.ab.data.datasource.subtask.SubTaskLocalDataSourceImpl
import com.ab.data.datasource.task.TaskLocalDataSource
import com.ab.data.datasource.task.TaskLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule  {
    @Binds
    @Singleton
    fun bindTaskLocalDataSource(impl: TaskLocalDataSourceImpl): TaskLocalDataSource

    @Binds
    @Singleton
    fun bindSubTaskLocalDataSource(impl: SubTaskLocalDataSourceImpl): SubTaskLocalDataSource
}
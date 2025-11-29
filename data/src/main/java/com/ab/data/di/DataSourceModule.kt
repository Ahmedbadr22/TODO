package com.ab.data.di

import com.ab.data.datasource.subtask.SubTaskLocalDataSource
import com.ab.data.datasource.subtask.SubTaskLocalDataSourceImpl
import com.ab.data.datasource.task.TaskLocalDataSource
import com.ab.data.datasource.task.TaskLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface DataSourceModule  {
    @Binds
    @ViewModelScoped
    fun bindTaskLocalDataSource(impl: TaskLocalDataSourceImpl): TaskLocalDataSource

    @Binds
    @ViewModelScoped
    fun bindSubTaskLocalDataSource(impl: SubTaskLocalDataSourceImpl): SubTaskLocalDataSource
}
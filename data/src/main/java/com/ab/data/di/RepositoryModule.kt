package com.ab.data.di

import com.ab.data.repository.SubTaskRepositoryImpl
import com.ab.data.repository.TaskRepositoryImpl
import com.ab.domain.respository.SubTaskRepository
import com.ab.domain.respository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule  {
    @Binds
    @Singleton
    fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository

    @Binds
    @Singleton
    fun bindSubTaskRepository(impl: SubTaskRepositoryImpl): SubTaskRepository
}
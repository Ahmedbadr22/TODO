package com.ab.di

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
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        impl: TaskRepositoryImpl
    ): TaskRepository

    @Binds
    @Singleton
    abstract fun bindSubTaskRepository(
        impl: SubTaskRepositoryImpl
    ): SubTaskRepository
}
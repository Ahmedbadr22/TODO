package com.ab.domain.di

import com.ab.domain.respository.SubTaskRepository
import com.ab.domain.respository.TaskRepository
import com.ab.domain.usecase.task.ListTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideListTasksUseCase(
        taskRepository: TaskRepository,
        subTaskRepository: SubTaskRepository
    ): ListTasksUseCase = ListTasksUseCase(taskRepository, subTaskRepository)
}

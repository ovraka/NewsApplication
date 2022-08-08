package com.assignment.newsapplication.module

import com.assignment.api_service.service.EverythingService
import com.assignment.api_service.service.SourceService
import com.assignment.api_service.usecase.CategoryUseCase
import com.assignment.api_service.usecase.EverythingPagingUseCase
import com.assignment.api_service.usecase.SourceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideCategoryUseCase() = CategoryUseCase()

    @Provides
    fun provideSourceUseCase(sourceService: SourceService) = SourceUseCase(sourceService)

    @Provides
    fun provideEverythingPagingUseCase(everythingService: EverythingService) =
        EverythingPagingUseCase(everythingService)
}
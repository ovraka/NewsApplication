package com.assignment.api_service.usecase

import com.assignment.api_service.service.SourceService
import com.assignment.common.base.AppResponse
import com.assignment.common.entity.source.SourceResponse
import kotlinx.coroutines.flow.flow

class SourceUseCase(val sourceService: SourceService) {
    operator fun invoke(category: String? = null) = flow<AppResponse<SourceResponse>> {
        try {
            emit(AppResponse.loading())
            val result = sourceService.getSources(category = category)
            result.body()?.let {
                emit(AppResponse.success(it))
            } ?: run {
                emit(AppResponse.failure(Exception("Null Data")))
            }
        } catch (e: Exception) {
            emit(AppResponse.failure(e))
        }
    }
}
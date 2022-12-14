package com.assignment.api_service.service

import com.assignment.common.entity.everything.EverythingResponse
import com.assignment.common.extention.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EverythingService {
    @GET("everything")
    suspend fun getEverything(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("q") q: String? = null,
        @Query("sources") sources: String,
        @Query("pageSize") pageSize: Int = Constants.DEFAULT_PAGE_SIZE,
        @Query("page") page: Int = 1
    ): Response<EverythingResponse>
}
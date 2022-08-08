package com.assignment.api_service.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.api_service.service.EverythingService
import com.assignment.common.entity.everything.Article

class EverythingDataSource(
    private val everythingService: EverythingService,
    private val sources: List<String>,
    private val pageSize: Int,
    private val q: String?
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val result = everythingService.getEverything(
                q = q, sources = sources.joinToString(separator = ","), page = params.key ?: 1
            )
            val paging = params.key ?: 1
            return if (result.isSuccessful) {
                result.body()?.let {
                    val totalPage = it.totalResults / pageSize
                    LoadResult.Page(
                        data = it.articles, if (paging == 1) null else paging - 1,
                        if (paging < totalPage && paging < 10) paging + 1 else null
                    )
                } ?: LoadResult.Error(Exception("Invalid Data"))
            } else {
                if (result.code() == 426) {

                }
                LoadResult.Error(Exception("Invalid Code"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
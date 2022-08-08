package com.assignment.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.assignment.api_service.service.EverythingService
import com.assignment.common.entity.everything.Article

object EverythingPager {
    fun createPager(
        pageSize: Int,
        everythingService: EverythingService,
        q: String?,
        sources: List<String>
    ): Pager<Int, Article> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            EverythingDataSource(everythingService, sources, pageSize, q)
        }
    )
}
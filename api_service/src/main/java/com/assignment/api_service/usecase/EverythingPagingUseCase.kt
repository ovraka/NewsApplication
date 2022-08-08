package com.assignment.api_service.usecase

import com.assignment.api_service.paging.EverythingPager
import com.assignment.api_service.service.EverythingService
import com.assignment.common.extention.Constants

class EverythingPagingUseCase(val everythingService: EverythingService) {
    operator fun invoke(q: String?, sources: List<String>) =
        EverythingPager.createPager(Constants.DEFAULT_PAGE_SIZE, everythingService, q, sources).flow
}
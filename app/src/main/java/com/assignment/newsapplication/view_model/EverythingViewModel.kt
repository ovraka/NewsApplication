package com.assignment.newsapplication.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.assignment.api_service.usecase.EverythingPagingUseCase
import com.assignment.common.base.BaseViewModel
import com.assignment.common.entity.everything.Article
import com.assignment.common.entity.source.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EverythingViewModel @Inject constructor(
    application: Application,
    val everythingPagingUseCase: EverythingPagingUseCase
) : BaseViewModel(application) {

    val searchText = MutableLiveData<String>()
    val pagingData = MutableLiveData<PagingData<Article>>()

    fun getEverything(source: Source) {
        viewModelScope.launch {
            everythingPagingUseCase(q = searchText.value, sources = listOf(source.id)).collect {
                pagingData.postValue(it)
            }
        }
    }
}
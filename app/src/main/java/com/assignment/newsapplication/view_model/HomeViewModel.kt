package com.assignment.newsapplication.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.api_service.usecase.CategoryUseCase
import com.assignment.api_service.usecase.SourceUseCase
import com.assignment.common.base.AppResponse
import com.assignment.common.base.BaseViewModel
import com.assignment.common.entity.source.Source
import com.assignment.common.entity.source.SourceResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    val categoryUseCase: CategoryUseCase,
    val sourceUseCase: SourceUseCase
) : BaseViewModel(application) {
    val categoryData = MutableLiveData<List<String>>()
    val sourceData = MutableLiveData<AppResponse<SourceResponse>>()
    val selectCategory = MutableLiveData<String>()

    init {
        getCategory()
        getSource()
    }

    fun getCategory() {
        viewModelScope.launch {
            categoryUseCase.invoke().collect {
                categoryData.postValue(it)
            }
        }
    }

    fun getSource() {
        viewModelScope.launch {
            sourceUseCase.invoke(selectCategory.value).collect {
                sourceData.postValue(it)
            }
        }
    }

    fun filter(q: String): List<Source> = sourceData.value?.let {
        if (it is AppResponse.AppResponseSuccess) {
            it.data?.sources.orEmpty().filter {
                it.name.contains(q, true)
            }
        } else {
            arrayListOf()
        }
    } ?: run {
        arrayListOf()
    }
}
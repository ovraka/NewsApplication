package com.assignment.newsapplication.fragment.home

import android.view.View
import androidx.core.widget.addTextChangedListener
import com.assignment.common.entity.source.Source

fun HomeFragment.selectCategory(category: String) {
    if (category == vm.selectCategory.value) {
        vm.selectCategory.value = null
    }else{
        vm.selectCategory.value = category
    }
}

fun HomeFragment.selectSource(source: Source) {
    vm.navigate(HomeFragmentDirections.homeToEverything(source))
}

fun HomeFragment.observeResourceFlow() {
    vm.selectCategory.observe(this) {
        vm.getSource()
    }
    binding.recyclerCategories.adapter = categoryAdapter
    vm.categoryData.observe(this) {
        categoryAdapter.sendData(it)
    }
    vm.getCategory()
    binding.search.addTextChangedListener {
        val data = vm.filter(it.toString())
        sourceAdapter.listData.submitList(data)
    }
    binding.recyclerSources.adapter = sourceAdapter
    observeResponseData(vm.sourceData, success = {
        sourceAdapter.sendData(it.sources)
        binding.loading.visibility = View.GONE
        binding.retryButton.visibility = View.GONE
        binding.bgLoading.visibility = View.GONE
    }, error = {
        binding.retryButton.visibility = View.VISIBLE
        binding.retryButton.setOnClickListener {
            vm.getSource()
        }
        binding.loading.visibility = View.GONE
        binding.bgLoading.visibility = View.GONE
    }, loading = {
        binding.retryButton.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
        binding.bgLoading.visibility = View.VISIBLE
    })
}
package com.assignment.newsapplication.fragment.everything

import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.paging.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun EverythingFragment.selectEverything(url: String) {
    vm.navigate(EverythingFragmentDirections.everythingToEverythingDetail(url))
}

fun EverythingFragment.observeResourceFlow() {
    binding.recycler.adapter = adapter.withLoadStateFooter(loadState)
    vm.getEverything(navFragmentArgs.source)
    vm.pagingData.observe(this) {
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
    binding.textBack.setOnClickListener {
        vm.popBackStack()
    }
    binding.search.addTextChangedListener {
        vm.searchText.value = it.toString()
        vm.getEverything(navFragmentArgs.source)
    }
    adapter.addLoadStateListener {
        if (it.append is LoadState.Error || it.refresh is LoadState.Error) {
            binding.recycler.visibility = View.GONE
            binding.loading.visibility = View.GONE
            binding.bgLoading.visibility = View.GONE
            binding.retryButton.visibility = View.VISIBLE
            binding.retryButton.setOnClickListener {
                vm.getEverything(navFragmentArgs.source)
            }
        } else if (it.refresh is LoadState.Loading) {
            binding.recycler.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
            binding.bgLoading.visibility = View.VISIBLE
        } else {
            binding.recycler.visibility = View.VISIBLE
            binding.retryButton.visibility = View.GONE
            binding.loading.visibility = View.GONE
            binding.bgLoading.visibility = View.GONE
        }
    }
}
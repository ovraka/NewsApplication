package com.assignment.newsapplication.fragment.home

import androidx.fragment.app.viewModels
import com.assignment.common.base.BaseFragment
import com.assignment.newsapplication.R
import com.assignment.newsapplication.adapter.category.CategoryAdapter
import com.assignment.newsapplication.adapter.source.SourceAdapter
import com.assignment.newsapplication.databinding.LayoutHomeFragmentBinding
import com.assignment.newsapplication.view_model.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<HomeViewModel, LayoutHomeFragmentBinding>() {
    override val vm: HomeViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_home_fragment
    val categoryAdapter = CategoryAdapter(::selectCategory)
    val sourceAdapter = SourceAdapter(::selectSource)

    override fun initBinding(binding: LayoutHomeFragmentBinding) {
        super.initBinding(binding)
        observeResourceFlow()
    }
}
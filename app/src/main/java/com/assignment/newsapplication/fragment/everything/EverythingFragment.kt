package com.assignment.newsapplication.fragment.everything

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.assignment.common.base.BaseFragment
import com.assignment.newsapplication.R
import com.assignment.newsapplication.adapter.everything.EverythingPagingAdapter
import com.assignment.newsapplication.adapter.everything.EverythingPagingStateAdapter
import com.assignment.newsapplication.databinding.LayoutEverythingFragmentBinding
import com.assignment.newsapplication.view_model.EverythingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EverythingFragment : BaseFragment<EverythingViewModel, LayoutEverythingFragmentBinding>() {
    override val vm: EverythingViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_everything_fragment
    val adapter = EverythingPagingAdapter(::selectEverything)
    val loadState = EverythingPagingStateAdapter()
    val navFragmentArgs: EverythingFragmentArgs by navArgs()

    override fun initBinding(binding: LayoutEverythingFragmentBinding) {
        super.initBinding(binding)
        observeResourceFlow()
    }
}
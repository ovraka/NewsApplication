package com.assignment.newsapplication.fragment.everything

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.assignment.newsapplication.databinding.LayoutEverythingDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EverythingFragmentDetail : Fragment() {

    val navDestination: EverythingFragmentDetailArgs by navArgs()
    lateinit var binding: LayoutEverythingDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutEverythingDetailBinding.inflate(inflater, container, false)
        binding.webView.loadUrl(navDestination.webView)
        return binding.root
    }

}
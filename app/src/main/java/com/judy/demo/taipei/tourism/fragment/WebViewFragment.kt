package com.judy.demo.taipei.tourism.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.judy.demo.taipei.tourism.R
import com.judy.demo.taipei.tourism.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment() {

    lateinit var binding : FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebViewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.WebView.apply {
            settings.apply {
                javaScriptEnabled = true

            }
            loadUrl("https://www.google.com/")
        }


    }

    override fun onDetach() {
        super.onDetach()
        binding.WebView.clearCache(true)
    }

}
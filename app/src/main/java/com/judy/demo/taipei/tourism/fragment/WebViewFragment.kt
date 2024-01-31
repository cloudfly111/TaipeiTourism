package com.judy.demo.taipei.tourism.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
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
        val title: String = arguments?.getString("TITLE") ?: ""
        val url: String = arguments?.getString("URL") ?: ""
        Log.v("AttractionsFragment", "url=$url")
        binding.TopLayout.AppBar.apply {
            this.title = title
            this.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            menu.clear()
        }

        binding.WebView.apply {
            settings.apply {
                javaScriptEnabled = true
                webChromeClient = object :WebChromeClient(){
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        binding.Progress.setProgress(newProgress*1000,true)
                    }
                }
                webViewClient = WebViewClient()
            }
            if(url.isNotEmpty()) loadUrl(url)
        }


    }


    override fun onDetach() {
        super.onDetach()
        binding.WebView.clearCache(true)
    }

}
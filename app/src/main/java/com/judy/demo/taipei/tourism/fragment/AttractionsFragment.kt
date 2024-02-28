package com.judy.demo.taipei.tourism.fragment

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navArgument
import com.judy.demo.taipei.tourism.MainActivity
import com.judy.demo.taipei.tourism.R
import com.judy.demo.taipei.tourism.adapter.ViewPagerAdapter
import com.judy.demo.taipei.tourism.databinding.FragmentAttractionsBinding
import com.judy.demo.taipei.tourism.utils.getLocaleTag
import com.judy.demo.taipei.tourism.utils.setAppLocale


class AttractionsFragment : Fragment() {
    lateinit var binding: FragmentAttractionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttractionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id: String = arguments?.getString("ID") ?: ""
        val mainActivity = requireActivity() as MainActivity
        val itemData = mainActivity.viewModel.findAttractionsDataFromId(id)

        binding.TopLayout.AppBar.apply {
            setTitle(itemData.name)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            menu.clear()
        }

        binding.ViewPager2.apply {
            val imageList = itemData.images.map { it.src }
            Log.v("Attractions", "imageList=$imageList")
            val adpter = ViewPagerAdapter(imageList)
            this.adapter = adpter
            adpter.notifyDataSetChanged()
        }

        binding.WebSiteTextView.setOnClickListener {
            val bundle = Bundle().apply {
                putString("TITLE", itemData.name)
                putString("URL", itemData.official_site)
            }
            findNavController().navigate(
                R.id.action_from_attractionsFragment_to_webViewFragment,
                bundle
            )
        }
        //設定動態ScrollView高度
        val heightDp = resources.displayMetrics.run { heightPixels / density }
        var layout = binding.ContentScrollView.layoutParams
        layout.height = heightDp.toInt()
        binding.ContentTextView.text = itemData.introduction

        mainActivity.viewModel.languageType.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                val newResource = context?.setAppLocale(getLocaleTag(it))?.resources!!
                updateLanguageStringFromResource(newResource)
            })

    }

    fun updateLanguageStringFromResource(resource: Resources) {
        binding.apply {
            WebSiteTextView.text = resource.getString(R.string.officialSite) ?: ""
        }
    }

}
package com.judy.demo.taipei.tourism.fragment

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.judy.demo.taipei.tourism.MainActivity
import com.judy.demo.taipei.tourism.R
import com.judy.demo.taipei.tourism.adapter.AttractionsAdapter
import com.judy.demo.taipei.tourism.adapter.NewsAdapter
import com.judy.demo.taipei.tourism.databinding.FragmentMainBinding
import com.judy.demo.taipei.tourism.databinding.FragmentWebViewBinding
import com.judy.demo.taipei.tourism.repository.AttractionsRepo
import com.judy.demo.taipei.tourism.repository.dataClass.APIStruct
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionsData
import com.judy.demo.taipei.tourism.repository.dataClass.NewsData
import com.judy.demo.taipei.tourism.repository.dataClass.NewsMessage
import com.judy.demo.taipei.tourism.repository.languageType.LanguageType
import com.judy.demo.taipei.tourism.repository.network.RetrofitInstance
import com.judy.demo.taipei.tourism.utils.getLocaleTag
import com.judy.demo.taipei.tourism.utils.setAppLocale
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainFragment : Fragment() {
    lateinit var binding : FragmentMainBinding
    private var currentLanguageType :LanguageType= LanguageType.ZH_TW
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mainActivity = requireActivity() as MainActivity
        val attractionsAdapter = AttractionsAdapter()
        val newsAdapter = NewsAdapter()

        binding.apply {
            TopLayout.AppBar.apply {
                navigationIcon = null
                setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem?): Boolean {
                        showLanguageSettingDialog(requireContext())
                        return true
                    }

                })
            }
            AttractionsRecyclerView.adapter = attractionsAdapter

            mainActivity.viewModel.attractionsData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                attractionsAdapter.submitList(mainActivity.viewModel.modifiyAttractionsData())
            })

            NewsRecyclerView.adapter = newsAdapter


            mainActivity.viewModel.newsData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                NewsRecyclerView.isVisible=it.isNotEmpty()
                NoDataLayout.root.isVisible=it.isEmpty()
                newsAdapter.submitList(mainActivity.viewModel.modifiyNewsData())
            })

            mainActivity.viewModel.languageType.observe(viewLifecycleOwner,androidx.lifecycle.Observer {
                val newResource =context?.setAppLocale(getLocaleTag(it))?.resources!!
                updateLanguageStringFromResource(newResource)
                currentLanguageType = it
            })

        }

    }


    fun showLanguageSettingDialog(context: Context) {
        val resource = context?.setAppLocale(getLocaleTag(currentLanguageType))?.resources!!
        val title = resource.getString(R.string.selectLanguage)
        val languageOptionList = arrayOf(
            resource.getString(R.string.traditionalChinese),
            resource.getString(R.string.simplifiedChinese),
            resource.getString(R.string.english),
            resource.getString(R.string.japanese),
            resource.getString(R.string.koreanLanguage),
            resource.getString(R.string.spanish),
            resource.getString(R.string.indonesian),
            resource.getString(R.string.thai),
            resource.getString(R.string.vietnamese)
        )
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder
            .setIcon(android.R.drawable.ic_menu_set_as)
            .setTitle(title)
            .setItems(languageOptionList) { dialog, which ->
                (requireActivity() as MainActivity).viewModel.languageType.value =
                    LanguageType.values()[which]
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun updateLanguageStringFromResource(resource:Resources){
        binding.apply {
            TopLayout.AppBar.title = resource.getString(R.string.mainTitle) ?: ""
            NewsTitleTextView.text = resource.getString(R.string.newsTitle) ?: ""
            AttractionsTitleTextView.text = resource.getString(R.string.attractionTitle) ?: ""
            NoDataLayout.noDataTextView.text = resource.getString(R.string.noData) ?: ""
        }
    }


}
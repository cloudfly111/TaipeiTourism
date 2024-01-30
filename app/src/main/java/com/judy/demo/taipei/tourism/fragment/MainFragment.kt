package com.judy.demo.taipei.tourism.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
import com.judy.demo.taipei.tourism.repository.network.RetrofitInstance
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainFragment : Fragment() {
    lateinit var binding : FragmentMainBinding

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
                setNavigationIcon(null)
                setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem?): Boolean {
                        showLanguageSettingDialog(requireContext())
                        return true
                    }

                })
            }
            AttractionsRecyclerView.adapter = attractionsAdapter
            RetrofitInstance.service.getAttractionsData("en",1).enqueue(object :
                Callback<APIStruct<AttractionsData>> {
                override fun onResponse(
                    call: Call<APIStruct<AttractionsData>>,
                    response: Response<APIStruct<AttractionsData>>
                ) {

                    if(response.isSuccessful){
                        Log.v("MainActivity","response.isSuccessful=${response.body()!!.data}")
                       mainActivity.viewModel.attractionsData.value = response.body()!!.data

                    }else{
                        Log.v("MainActivity","response.isSuccessful=${response.body()!!.total}")

                    }
                }

                override fun onFailure(call: Call<APIStruct<AttractionsData>>, t: Throwable) {
                    Log.v("MainActivity","onFailure:$t")
                }

            })


            mainActivity.viewModel.attractionsData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                Log.v("","it=$it")
                attractionsAdapter.submitList(mainActivity.viewModel.modifiyAttractionsData())
            })

            NewsRecyclerView.adapter = newsAdapter
            RetrofitInstance.service.getNewsData("en", 1).enqueue(object :
                Callback<APIStruct<NewsData>> {
                override fun onResponse(
                    call: Call<APIStruct<NewsData>>,
                    response: Response<APIStruct<NewsData>>
                ) {
                    if(response.isSuccessful){
                        Log.v("MainActivity","response.isSuccessful=${response.body()!!.data}")
                        mainActivity.viewModel.newsData.value = response.body()!!.data

                    }else{
                        Log.v("MainActivity","response.isSuccessful=${response.body()!!.total}")

                    }
                }

                override fun onFailure(call: Call<APIStruct<NewsData>>, t: Throwable) {
                    Log.v("MainActivity","onFailure:$t")
                }

            })

            mainActivity.viewModel.newsData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                Log.v("","it=$it")
                newsAdapter.submitList(mainActivity.viewModel.modifiyNewsData())
            })


        }



    }


    fun showLanguageSettingDialog(context: Context){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder
            .setTitle("請選取需切換的語言")
            .setPositiveButton("確定") { dialog, which ->
                // Do something.
            }
            .setNegativeButton("取消") { dialog, which ->
                // Do something else.
            }
            .setItems(arrayOf("Item One", "Item Two", "Item Three")) { dialog, which ->
                // Do something on item tapped.
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}
package com.judy.demo.taipei.tourism

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.judy.demo.taipei.tourism.databinding.ActivityMainBinding
import com.judy.demo.taipei.tourism.repository.dataClass.APIStruct
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionsData
import com.judy.demo.taipei.tourism.repository.dataClass.NewsData
import com.judy.demo.taipei.tourism.repository.languageType.LanguageType
import com.judy.demo.taipei.tourism.repository.network.RetrofitInstance
import com.judy.demo.taipei.tourism.utils.setAppLocale
import com.judy.demo.taipei.tourism.viewModel.TourismViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel by viewModels<TourismViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.languageType.observe(this, Observer {
            RetrofitInstance.service.getAttractionsData(it.type).enqueue(object :
                Callback<APIStruct<AttractionsData>> {
                override fun onResponse(
                    call: Call<APIStruct<AttractionsData>>,
                    response: Response<APIStruct<AttractionsData>>
                ) {

                    if (response.isSuccessful) {
                        viewModel.attractionsData.value = response.body()!!.data
                    }
                }

                override fun onFailure(call: Call<APIStruct<AttractionsData>>, t: Throwable) {
                    Log.v("MainActivity", "onFailure:$t")
                }

            })
            RetrofitInstance.service.getNewsData(it.type).enqueue(object :
                Callback<APIStruct<NewsData>> {
                override fun onResponse(
                    call: Call<APIStruct<NewsData>>,
                    response: Response<APIStruct<NewsData>>
                ) {
                    if (response.isSuccessful) {
                        viewModel.newsData.value = response.body()!!.data
                    }
                }

                override fun onFailure(call: Call<APIStruct<NewsData>>, t: Throwable) {
                    Log.v("MainActivity", "onFailure:$t")
                }

            })

        })

    }


}
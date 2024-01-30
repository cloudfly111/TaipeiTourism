package com.judy.demo.taipei.tourism

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.judy.demo.taipei.tourism.databinding.ActivityMainBinding
import com.judy.demo.taipei.tourism.utils.setAppLocale
import com.judy.demo.taipei.tourism.viewModel.TourismViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    val viewModel by viewModels<TourismViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ContextWrapper(newBase!!.setAppLocale("en")))
    }
}
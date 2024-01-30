package com.judy.demo.taipei.tourism.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.judy.demo.taipei.tourism.R
import com.judy.demo.taipei.tourism.databinding.FragmentAttractionsBinding


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

    }

}
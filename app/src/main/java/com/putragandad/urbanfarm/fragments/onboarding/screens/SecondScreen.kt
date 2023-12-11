package com.putragandad.urbanfarm.fragments.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.FragmentSecondScreenBinding


class SecondScreen : Fragment() {
    private var _binding : FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.btnNextOb2.setOnClickListener{
            viewPager?.currentItem = 2
        }

        binding.btnPrevOb2.setOnClickListener{
            viewPager?.currentItem = 0
        }

        return binding.root
    }
}
package com.putragandad.urbanfarm.fragments.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.FragmentFirstScreenBinding

class FirstScreen : Fragment() {
    private var _binding : FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.btnNextOb1.setOnClickListener{
            viewPager?.currentItem = 1
        }

        return binding.root
    }
}
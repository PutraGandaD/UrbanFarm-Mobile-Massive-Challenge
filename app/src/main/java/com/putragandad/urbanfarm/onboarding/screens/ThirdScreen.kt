package com.putragandad.urbanfarm.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.FragmentSecondScreenBinding
import com.putragandad.urbanfarm.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment() {
    private var _binding : FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.btnPrevOb3.setOnClickListener{
            viewPager?.currentItem = 1
        }

        binding.btnDoneOb3.setOnClickListener{
            findNavController().navigate(R.id.action_onboarding_to_landing)
            onBoardingFinished()
        }

        return binding.root
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}
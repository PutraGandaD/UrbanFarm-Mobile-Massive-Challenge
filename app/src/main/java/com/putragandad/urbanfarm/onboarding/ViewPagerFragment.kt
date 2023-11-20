package com.putragandad.urbanfarm.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.FragmentViewPagerBinding
import com.putragandad.urbanfarm.onboarding.screens.FirstScreen
import com.putragandad.urbanfarm.onboarding.screens.SecondScreen
import com.putragandad.urbanfarm.onboarding.screens.ThirdScreen

class ViewPagerFragment : Fragment() {
    private var _binding : FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        return binding.root
    }
}
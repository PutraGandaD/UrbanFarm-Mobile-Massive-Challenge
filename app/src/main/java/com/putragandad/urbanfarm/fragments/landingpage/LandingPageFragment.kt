package com.putragandad.urbanfarm.fragments.landingpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.FragmentLandingPageBinding

class LandingPageFragment : Fragment() {
    private var _binding : FragmentLandingPageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLandingPageBinding.inflate(inflater, container, false)

        binding.btnSigninLanding.setOnClickListener{
            findNavController().navigate(R.id.action_landingPageFragment_to_loginPageFragment)
        }

        binding.btnSignupLanding.setOnClickListener{
            findNavController().navigate(R.id.action_landingPageFragment_to_registerPageFragment)
        }

        return binding.root
    }
}
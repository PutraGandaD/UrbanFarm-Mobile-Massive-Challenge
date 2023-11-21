package com.putragandad.urbanfarm.fragments.registerpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.FragmentRegisterPageBinding

class RegisterPageFragment : Fragment() {
    private var _binding : FragmentRegisterPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterPageBinding.inflate(inflater, container, false)

        return binding.root
    }
}
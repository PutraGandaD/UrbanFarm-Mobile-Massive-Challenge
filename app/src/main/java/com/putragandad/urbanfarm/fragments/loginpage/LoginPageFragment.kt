package com.putragandad.urbanfarm.fragments.loginpage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.FragmentLoginPageBinding

class LoginPageFragment : Fragment() {
    private var _binding : FragmentLoginPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginPageBinding.inflate(inflater, container, false)

        binding.btnLoginPage.setOnClickListener{
            findNavController().navigate(R.id.action_login_to_beranda)
            loginFinished()
        }

        return binding.root
    }

    private fun loginFinished() {
        val sharedPref = requireActivity().getSharedPreferences("loginFinished", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}
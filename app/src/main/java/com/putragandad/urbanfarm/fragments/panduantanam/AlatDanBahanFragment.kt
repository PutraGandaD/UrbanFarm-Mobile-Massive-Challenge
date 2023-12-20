package com.putragandad.urbanfarm.fragments.panduantanam

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.FragmentAlatDanBahanPageBinding
import com.putragandad.urbanfarm.databinding.FragmentBerandaPageBinding

class AlatDanBahanFragment : Fragment() {
    private var _binding : FragmentAlatDanBahanPageBinding? = null
    private val binding get() = _binding!!

    private var namaTanaman: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlatDanBahanPageBinding.inflate(inflater, container, false)

        namaTanaman = setNamaTanamanValue()
        binding.tvNamaTanaman.text = namaTanaman
        Toast.makeText(requireContext(), "Get Nama Tanaman from bottomsheet $namaTanaman success", Toast.LENGTH_SHORT).show()

        return binding.root
    }

    private fun setNamaTanamanValue() : String? {
        val sharedPreferences = requireContext().getSharedPreferences("BottomSheetNamaTanaman", Context.MODE_PRIVATE)
        return sharedPreferences.getString("namaTanaman", "")
    }
}
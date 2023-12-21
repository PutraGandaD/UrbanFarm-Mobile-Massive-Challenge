package com.putragandad.urbanfarm.fragments.panduantanam.tablayout.hidroponik

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.tablayout_tanaman.hidroponik.panduantanam.FirstStepFragmentHidroponikContainer
import com.putragandad.urbanfarm.activity.tablayout_tanaman.polybag.panduantanam.FirstStepFragmentPolybagContainer
import com.putragandad.urbanfarm.databinding.FragmentPanduanTanamHidroponikBinding
import com.putragandad.urbanfarm.databinding.FragmentPanduanTanamPolybagBinding

class PanduanTanamHidroponikFragment : Fragment() {
    private var _binding: FragmentPanduanTanamHidroponikBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPanduanTanamHidroponikBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnMulaiStep1 = binding.btnMulaiStep1

        btnMulaiStep1.setOnClickListener {
            startActivity(Intent(requireActivity(), FirstStepFragmentHidroponikContainer::class.java))
        }
    }

}
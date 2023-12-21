package com.putragandad.urbanfarm.fragments.panduantanam.tablayout.polybag

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.tablayout_tanaman.polybag.panduantanam.FirstStepFragmentPolybagContainer
import com.putragandad.urbanfarm.databinding.FragmentPanduanTanamPolybagBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PanduanTanamPolybagFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PanduanTanamPolybagFragment : Fragment() {
    private var _binding: FragmentPanduanTanamPolybagBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPanduanTanamPolybagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnMulaiStep1 = binding.btnMulaiStep1

        btnMulaiStep1.setOnClickListener {
            startActivity(Intent(requireActivity(), FirstStepFragmentPolybagContainer::class.java))
        }
    }
}
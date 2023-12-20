package com.putragandad.urbanfarm.fragments.berandapage.modalbottomsheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.putragandad.urbanfarm.activity.tablayout_tanaman.polybag.TabLayoutPagePolybagActivity
import com.putragandad.urbanfarm.databinding.FragmentPilihMetodeTanamBinding

class PilihMetodeTanamBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentPilihMetodeTanamBinding
    private lateinit var namaTanaman: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        namaTanaman = arguments?.getString("namatanaman") ?: ""

        binding = FragmentPilihMetodeTanamBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMetodeTanamPolybag.setOnClickListener {
            Toast.makeText(requireContext(), "Debug : nama tanaman $namaTanaman, get from bundle", Toast.LENGTH_SHORT).show()
            when(namaTanaman) {
                "Bawang Merah" -> {
                    Toast.makeText(requireContext(), "Debug : nama tanaman $namaTanaman, get from bundle", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), TabLayoutPagePolybagActivity::class.java))
                }
            }
        }

        binding.btnMetodeTanamHidroponik.setOnClickListener {
            Toast.makeText(requireContext(), "Debug : nama tanaman $namaTanaman, get from bundle", Toast.LENGTH_SHORT).show()
            when(namaTanaman) {
                "Bawang Merah" -> {
                    Toast.makeText(requireContext(), "Debug : nama tanaman $namaTanaman, get from bundle", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}
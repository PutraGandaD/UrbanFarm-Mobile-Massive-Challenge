package com.putragandad.urbanfarm.fragments.panduantanam.kacangpanjang.polybag.mediatanam

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.tablayout_tanaman.polybag.TabLayoutPagePolybagActivity

class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_second_screen_kacangpanjang_polybag_mediatanam,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDone = view.findViewById<MaterialButton>(R.id.btn_next_kacangpanjang_polybag_mediatanam2)
        val btnPrev = view.findViewById<MaterialButton>(R.id.btn_previous_kacangpanjang_polybag_mediatanam2)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager_panduan_tanam_polybag_first_step)

        btnDone.setOnClickListener {
            val intent = Intent(requireContext(), TabLayoutPagePolybagActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        btnPrev.setOnClickListener {
            viewPager?.currentItem = 0
        }
    }
}
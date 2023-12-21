package com.putragandad.urbanfarm.fragments.panduantanam.cabai.polybag.menanam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.putragandad.urbanfarm.R
class NinthScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_ninth_screen_cabai_polybag_menanam,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext = view.findViewById<MaterialButton>(R.id.btn_next_cabai_polybag_menanam9)
        val btnPrev = view.findViewById<MaterialButton>(R.id.btn_previous_cabai_polybag_menanam9)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager_panduan_tanam_polybag_second_step)

        btnNext.setOnClickListener {
            viewPager?.currentItem = 9
        }

        btnPrev.setOnClickListener {
            viewPager?.currentItem = 7
        }
    }
}
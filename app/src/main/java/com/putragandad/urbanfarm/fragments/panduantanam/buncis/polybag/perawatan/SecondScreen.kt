package com.putragandad.urbanfarm.fragments.panduantanam.buncis.polybag.perawatan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.putragandad.urbanfarm.R
class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_second_screen_buncis_polybag_perawatan,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext = view.findViewById<MaterialButton>(R.id.btn_next_buncis_polybag_perawatan2)
        val btnPrev = view.findViewById<MaterialButton>(R.id.btn_previous_buncis_polybag_perawatan2)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager_perawatan_polybag)

        btnNext.setOnClickListener {
            viewPager?.currentItem = 2
        }

        btnPrev.setOnClickListener {
            viewPager?.currentItem = 0
        }
    }
}
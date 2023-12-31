package com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.hidroponik.perawatan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.tablayout_tanaman.hidroponik.TabLayoutPageHidroponikActivity

class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_second_screen_bawangmerah_hidroponik_perawatan,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDone = view.findViewById<MaterialButton>(R.id.btn_next_bawangmerah_hidroponik_perawatan2)
        val btnPrev = view.findViewById<MaterialButton>(R.id.btn_previous_bawangmerah_hidroponik_perawatan2)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager_perawatan_hidroponik)

        btnDone.setOnClickListener {
            val intent = Intent(requireContext(), TabLayoutPageHidroponikActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        btnPrev.setOnClickListener {
            viewPager?.currentItem = 0
        }
    }
}
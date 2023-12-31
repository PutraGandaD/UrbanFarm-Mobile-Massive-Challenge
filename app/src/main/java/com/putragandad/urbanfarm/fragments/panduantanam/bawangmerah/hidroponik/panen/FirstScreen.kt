package com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.hidroponik.panen

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

class FirstScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_first_screen_bawangmerah_hidroponik_panen,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDone = view.findViewById<MaterialButton>(R.id.btn_next_bawangmerah_hidroponik_panen1)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager_panduan_tanam_hidroponik_third_step)

        btnDone.setOnClickListener {
            val intent = Intent(requireContext(), TabLayoutPageHidroponikActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}
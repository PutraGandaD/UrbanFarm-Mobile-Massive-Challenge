package com.putragandad.urbanfarm.fragments.panduantanam.tablayout.polybag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.putragandad.urbanfarm.R

/**
 * A simple [Fragment] subclass.
 * Use the [PerawatanPagePolybagFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerawatanPagePolybagFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perawatan_page_polybag, container, false)
    }
}
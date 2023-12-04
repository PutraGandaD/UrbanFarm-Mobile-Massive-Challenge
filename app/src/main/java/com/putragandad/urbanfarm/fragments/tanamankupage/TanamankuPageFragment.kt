package com.putragandad.urbanfarm.fragments.tanamankupage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.tanamanku.AddEditTanamankuItemActivity
import com.putragandad.urbanfarm.databinding.FragmentTanamankuPageBinding

class TanamankuPageFragment : Fragment() {
    private var _binding : FragmentTanamankuPageBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTanamankuPageBinding.inflate(inflater, container, false)

        binding.fabTambahTanaman.setOnClickListener {
            val intent = Intent(context, AddEditTanamankuItemActivity::class.java)
            startActivity(intent)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView : RecyclerView = view.findViewById(R.id.rv_card_tanamanku_list_item)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

    }

}
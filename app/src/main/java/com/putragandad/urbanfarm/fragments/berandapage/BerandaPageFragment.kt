package com.putragandad.urbanfarm.fragments.berandapage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.adapters.beranda.ItemTanamanRVAdapter
import com.putragandad.urbanfarm.databinding.FragmentBerandaPageBinding
import com.putragandad.urbanfarm.models.beranda.ItemTanamanModels

class BerandaPageFragment : Fragment() {
    private var _binding : FragmentBerandaPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBerandaPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerView : RecyclerView = view.findViewById(R.id.rv_tanamanlist)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context,4)

        val itemTanamanList = ArrayList<ItemTanamanModels>()
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_bawangmerah,
            "Bawang Merah"))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_cabairawit,
            "Cabai Rawit"))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_buncis,
            "Buncis"))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_wortel,
            "Wortel"))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_kembangkol,
            "Kembang Kol"))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_tomat,
            "Tomat"))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_kacangpanjang,
            "Kacang Panjang"))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_timun,
            "Timun"))

        val itemTanamanAdapter = ItemTanamanRVAdapter(itemTanamanList)
        recyclerView.adapter = itemTanamanAdapter


    }
}
package com.putragandad.urbanfarm.fragments.berandapage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.beranda.DetailTanamanPageActivity
import com.putragandad.urbanfarm.adapters.beranda.IconTanamanRVAdapter
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
            "Bawang Merah", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.img_card_bawangmerah_tanamanku))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_cabairawit,
            "Cabai Rawit", "Cabai", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_cabai_merah), R.drawable.img_card_cabai_rawit_tanamanku))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_buncis,
            "Buncis", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.img_card_bawangmerah_tanamanku))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_wortel,
            "Wortel", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.img_card_bawangmerah_tanamanku))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_kembangkol,
            "Kembang Kol", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.img_card_bawangmerah_tanamanku))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_tomat,
            "Tomat", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.img_card_bawangmerah_tanamanku))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_kacangpanjang,
            "Kacang Panjang", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.img_card_bawangmerah_tanamanku))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_timun,
            "Timun", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.img_card_bawangmerah_tanamanku))

        val itemTanamanAdapter = IconTanamanRVAdapter(itemTanamanList)
        recyclerView.adapter = itemTanamanAdapter

        itemTanamanAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailTanamanPageActivity::class.java)
            intent.putExtra("item_tanaman_dashboard", it)
            startActivity(intent)
        }
    }
}
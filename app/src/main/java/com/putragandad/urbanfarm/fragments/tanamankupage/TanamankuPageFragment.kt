package com.putragandad.urbanfarm.fragments.tanamankupage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.tanamanku.AddTanamankuItemActivity
import com.putragandad.urbanfarm.adapters.tanamanku.TanamankuClickDeleteInterface
import com.putragandad.urbanfarm.adapters.tanamanku.TanamankuItemRVAdapter
import com.putragandad.urbanfarm.databinding.FragmentTanamankuPageBinding
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels
import com.putragandad.urbanfarm.viewmodels.tanamanku.TanamankuViewModel

class TanamankuPageFragment : Fragment(), TanamankuClickDeleteInterface {
    private var _binding : FragmentTanamankuPageBinding? = null
    lateinit var viewModel: TanamankuViewModel
    lateinit var tanamankuRV: RecyclerView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTanamankuPageBinding.inflate(inflater, container, false)

        binding.fabTambahTanaman.setOnClickListener {
            val intent = Intent(requireContext(), AddTanamankuItemActivity::class.java)
            startActivity(intent)
            //Log.d("TanamankuPageFragment", "Fab button clicked")
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tanamankuRV = view.findViewById(R.id.rv_card_tanamanku_list_item)
        tanamankuRV.setHasFixedSize(true)
        tanamankuRV.layoutManager = LinearLayoutManager(context)

        val tanamankuRVAdapter = TanamankuItemRVAdapter(requireContext(), this)
        tanamankuRV.adapter = tanamankuRVAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(TanamankuViewModel::class.java)

        viewModel.allTanaman.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                tanamankuRVAdapter.updateList(it)
            }
        })
    }

    override fun onDeleteIconClick(tanamanku: TanamankuItemModels) {
        viewModel.deleteTanaman(tanamanku)
        Toast.makeText(requireContext(), "Tanaman Anda berhasil dihapus", Toast.LENGTH_LONG).show()
    }

}
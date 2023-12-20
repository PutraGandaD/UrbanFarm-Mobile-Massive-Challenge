package com.putragandad.urbanfarm.fragments.berandapage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.beranda.DetailTanamanPageActivity
import com.putragandad.urbanfarm.adapters.beranda.IconTanamanRVAdapter
import com.putragandad.urbanfarm.adapters.beranda.VideosRVAdapter
import com.putragandad.urbanfarm.api.ApiRetrofit
import com.putragandad.urbanfarm.databinding.FragmentBerandaPageBinding
import com.putragandad.urbanfarm.models.api.JualPanenModel
import com.putragandad.urbanfarm.models.api.VideosDashboardModel
import com.putragandad.urbanfarm.models.beranda.ItemTanamanModels
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import kotlin.concurrent.timerTask

class BerandaPageFragment : Fragment() {
    private val api by lazy { ApiRetrofit().endpoint }
    private var _binding : FragmentBerandaPageBinding? = null
    private lateinit var videosDashboardRVAdapter: VideosRVAdapter
    private val binding get() = _binding!!
    private var tanamankuCount: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBerandaPageBinding.inflate(inflater, container, false)

        tanamankuCount = setTanamankuItemCount()
        binding.tvTitleCardTanamanku.setText("Anda Memiliki $tanamankuCount tanaman")

        getVideos()

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
            "Buncis", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.buncis))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_wortel,
            "Wortel", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.wortel))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_kembangkol,
            "Kembang Kol", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.img_card_bawangmerah_tanamanku))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_tomat,
            "Tomat", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.tomat))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_kacangpanjang,
            "Kacang Panjang", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.kacangpanjang))
        itemTanamanList.add(ItemTanamanModels(R.drawable.ic_home_timun,
            "Timun", "Sayur", "Hidroponik dan Polybag", "Mudah", requireContext().getString(R.string.deskripsi_bawang_merah), R.drawable.timun))

        val itemTanamanAdapter = IconTanamanRVAdapter(itemTanamanList)
        recyclerView.adapter = itemTanamanAdapter

        itemTanamanAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailTanamanPageActivity::class.java)
            intent.putExtra("item_tanaman_dashboard", it)
            startActivity(intent)
        }

        // initiate video dashboard
        var videosRV: RecyclerView = view.findViewById(R.id.rv_videoslist)
        val layoutManagerVideos = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        videosDashboardRVAdapter = VideosRVAdapter(arrayListOf())
        videosRV.adapter = videosDashboardRVAdapter
        videosRV.layoutManager = layoutManagerVideos
    }

    private fun getVideos() {
        api.getVideos().enqueue(object : Callback<VideosDashboardModel> {
            override fun onResponse(
                call: Call<VideosDashboardModel>,
                response: Response<VideosDashboardModel>
            ) {
                if(response.isSuccessful) {
                    val videosModel = response.body()
                    val listData = videosModel!!.data

                    if (listData != null && listData.isNotEmpty()) {
                        for (item in listData) {
                            Log.e("MainActivity", "ID : ${item.id}")
                        }
                    } else {
                        Log.e("MainActivity", "ListData is null or empty")

                    }
                    videosDashboardRVAdapter.setData(listData)
                    Log.e("JualPanenFragment", "Content-Type: ${response.headers().get("Content-Type")}")
                }
            }

            override fun onFailure(call: Call<VideosDashboardModel>, t: Throwable) {
                Log.e("MainActivity", t.toString())
            }
        })
    }

    private fun setTanamankuItemCount() : Int? {
        val sharedPreferences = requireContext().getSharedPreferences("TanamankuCount", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("itemCount", 0)
    }
}
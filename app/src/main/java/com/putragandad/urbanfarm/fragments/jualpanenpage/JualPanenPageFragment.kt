package com.putragandad.urbanfarm.fragments.jualpanenpage

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.activity.jualpanenpage.AddJualPanenPostActivity
import com.putragandad.urbanfarm.adapters.jualpanen.JualPanenRVAdapter
import com.putragandad.urbanfarm.api.ApiRetrofit
import com.putragandad.urbanfarm.databinding.FragmentJualPanenPageBinding
import com.putragandad.urbanfarm.databinding.FragmentTanamankuPageBinding
import com.putragandad.urbanfarm.models.api.JualPanenModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import kotlin.concurrent.timerTask

class JualPanenPageFragment : Fragment() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var jualPanenRVAdapter: JualPanenRVAdapter
    private lateinit var listJualPanen: RecyclerView
    private var _binding: FragmentJualPanenPageBinding? = null
    private val binding get() = _binding!!
    private var funtimer: Timer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJualPanenPageBinding.inflate(inflater, container, false)

        binding.fabBuatPostingan.setOnClickListener {
            val intent = Intent(requireContext(), AddJualPanenPostActivity::class.java)
            startActivity(intent)
        }

        listJualPanen = binding.rvCardJualpanenListItem
        jualPanenRVAdapter = JualPanenRVAdapter(arrayListOf())
        listJualPanen.adapter = jualPanenRVAdapter

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    private fun startTimer() {
        funtimer = Timer()
        funtimer?.scheduleAtFixedRate(
            timerTask() {
                getPost()
            }, 5000, 5000)
    }

    private fun stopTimer() {
        funtimer?.cancel()
        funtimer = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("JualPanenPageFragment", "onViewCreated")
        listJualPanen.layoutManager = LinearLayoutManager(requireContext())
        getPost()
    }

    private fun getPost() {
        api.data().enqueue(object : Callback<JualPanenModel> {
            override fun onResponse(
                call: Call<JualPanenModel>,
                response: Response<JualPanenModel>
            ) {
                if (response.isSuccessful) {
                    val alatTanamModel = response.body()
                    val listData = alatTanamModel!!.data

                    if (listData != null && listData.isNotEmpty()) {
                        Log.d("JualPanenPageFragment", "Data size: ${listData.size}")
                        for (item in listData) {
                            Log.e("MainActivity", "ID : ${item.id}")
                        }
                    } else {
                        Log.e("MainActivity", "ListData is null or empty")
                    }

                    jualPanenRVAdapter.setData(listData)
                    Log.d("JualPanenPageFragment", "Content-Type: ${response.headers().get("Content-Type")}")
                }
            }

            override fun onFailure(call: Call<JualPanenModel>, t: Throwable) {
                Log.e("MainActivity", t.toString())
            }
        })
    }
}

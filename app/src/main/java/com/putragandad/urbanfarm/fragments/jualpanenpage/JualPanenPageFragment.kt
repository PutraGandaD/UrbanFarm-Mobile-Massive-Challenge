package com.putragandad.urbanfarm.fragments.jualpanenpage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.adapters.jualpanen.JualPanenRVAdapter
import com.putragandad.urbanfarm.api.ApiRetrofit
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
    private lateinit var fabAdd : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jual_panen_page, container, false)
    }

    private fun timer() {
        val funtimer: Timer = Timer()
        funtimer.scheduleAtFixedRate(
            timerTask() {
                getNote()
            }, 5000, 5000)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initiate RecyclerView
        var recyclerView: RecyclerView = view.findViewById(R.id.rv_card_jualpanen_list_item)
        jualPanenRVAdapter = JualPanenRVAdapter(arrayListOf())
        recyclerView.adapter = jualPanenRVAdapter
        timer()
    }

    private fun getNote() {
        api.data().enqueue(object : Callback<JualPanenModel> {
            override fun onResponse(
                call: Call<JualPanenModel>,
                response: Response<JualPanenModel>
            ) {
                if(response.isSuccessful) {
                    val alatTanamModel = response.body()
                    val listData = alatTanamModel!!.data

                    if (listData != null && listData.isNotEmpty()) {
                        for (item in listData) {
                            Log.e("MainActivity", "ID : ${item.id}")
                        }
                    } else {
                        Log.e("MainActivity", "ListData is null or empty")

                    }
                    jualPanenRVAdapter.setData(listData)
                    Log.e("JualPanenFragment", "Content-Type: ${response.headers().get("Content-Type")}")
                }
            }

            override fun onFailure(call: Call<JualPanenModel>, t: Throwable) {
                Log.e("MainActivity", t.toString())
            }
        })
    }
}
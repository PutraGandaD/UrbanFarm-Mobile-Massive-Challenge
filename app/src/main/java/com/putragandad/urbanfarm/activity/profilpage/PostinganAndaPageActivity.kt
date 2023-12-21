package com.putragandad.urbanfarm.activity.profilpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.adapters.profilpage.postingansaya.PostinganSayaCardRVAdapter
import com.putragandad.urbanfarm.api.ApiRetrofit
import com.putragandad.urbanfarm.databinding.ActivityPostinganAndaPageBinding
import com.putragandad.urbanfarm.models.api.JualPanenModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import kotlin.concurrent.timerTask

class PostinganAndaPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostinganAndaPageBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var postinganSayaCardRVAdapter: PostinganSayaCardRVAdapter
    private lateinit var listPostinganSaya: RecyclerView

    lateinit var id_user: String

    private val api by lazy { ApiRetrofit().endpoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostinganAndaPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        setAdapter()
        getMyPost()
    }

    private fun setData() {
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser!!.uid
        id_user = uid.toString()
    }

    private fun setAdapter() {
        postinganSayaCardRVAdapter = PostinganSayaCardRVAdapter(arrayListOf())
        listPostinganSaya = findViewById(R.id.rv_card_postingansaya_list_item)
        listPostinganSaya.adapter = postinganSayaCardRVAdapter

    }

    private fun getMyPost() {
        api.dataByIdUser(id_user).enqueue(object : Callback<JualPanenModel> {
            override fun onResponse(
                call: Call<JualPanenModel>,
                response: Response<JualPanenModel>
            ) {
                if(response.isSuccessful) {
                    val alatTanamModel = response.body()
                    val listData = alatTanamModel?.data

                    if (listData != null && listData.isNotEmpty()) {
                        postinganSayaCardRVAdapter.setData(listData)
                    } else {
                        Log.e("MainActivity", "ListData is null or empty")

                    }
                    Log.e("MainActivity", "Content-Type: ${response.headers().get("Content-Type")}")
                }
            }

            override fun onFailure(call: Call<JualPanenModel>, t: Throwable) {
                Log.e("MainActivity", t.toString())
            }
        })
    }
}
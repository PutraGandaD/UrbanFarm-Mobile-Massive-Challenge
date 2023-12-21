package com.putragandad.urbanfarm.fragments.panduantanam.tablayout.hidroponik

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.adapters.tablayout_tanaman_adapter.alatdanbahancard.AlatCardRVAdapter
import com.putragandad.urbanfarm.adapters.tablayout_tanaman_adapter.alatdanbahancard.BahanCardRVAdapter
import com.putragandad.urbanfarm.api.ApiRetrofit
import com.putragandad.urbanfarm.databinding.FragmentAlatDanBahanHidroponikBinding
import com.putragandad.urbanfarm.databinding.FragmentAlatDanBahanPolybagBinding
import com.putragandad.urbanfarm.models.api.AlatTanamModel
import com.putragandad.urbanfarm.models.api.BahanTanamModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlatDanBahanHidroponikFragment : Fragment() {
    private val api by lazy { ApiRetrofit().endpoint }
    private var _binding : FragmentAlatDanBahanHidroponikBinding? = null
    private val binding get() = _binding!!

    private lateinit var alatCardRVAdapter: AlatCardRVAdapter
    private lateinit var bahanCardRVAdapter: BahanCardRVAdapter

    private var namaTanaman: String? = null
    private var metodeTanam: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlatDanBahanHidroponikBinding.inflate(inflater, container, false)

        namaTanaman = setNamaTanamanValue()
        metodeTanam = setMetodeTanamValue()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initiate alat rv
        var alatRecyclerView : RecyclerView = view.findViewById(R.id.rv_alat_card)
        alatCardRVAdapter = AlatCardRVAdapter(arrayListOf())
        alatRecyclerView.adapter = alatCardRVAdapter
        getAlat()

        //initiate bahan rv
        var bahanRecyclerView : RecyclerView = view.findViewById(R.id.rv_bahan_card)
        bahanCardRVAdapter = BahanCardRVAdapter(arrayListOf())
        bahanRecyclerView.adapter = bahanCardRVAdapter
        getBahan()
    }


    private fun setNamaTanamanValue() : String? {
        val sharedPreferences = requireContext().getSharedPreferences("BottomSheetNamaTanaman",
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString("namaTanaman", "")
    }

    private fun setMetodeTanamValue() : String? {
        val sharedPreferences = requireContext().getSharedPreferences("BottomSheetNamaTanaman",
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString("metodeTanam", "")
    }

    private fun getAlat() {
        api.getAlat(namaTanaman, metodeTanam).enqueue(object : Callback<AlatTanamModel> {
            override fun onResponse(
                call: Call<AlatTanamModel>,
                response: Response<AlatTanamModel>
            ) {
                if(response.isSuccessful) {
                    val alatModel = response.body()
                    val listData = alatModel?.data

                    if(listData != null) {
                        alatCardRVAdapter.setData(listData)
                    } else {
                        Toast.makeText(requireContext(), "API DATA for alat is null", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<AlatTanamModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun getBahan() {
        api.getBahan(namaTanaman, metodeTanam).enqueue(object: Callback<BahanTanamModel> {
            override fun onResponse(
                call: Call<BahanTanamModel>,
                response: Response<BahanTanamModel>
            ) {
                if(response.isSuccessful) {
                    val bahanModel = response.body()
                    val listData = bahanModel?.data

                    if(listData != null) {
                        bahanCardRVAdapter.setData(listData)
                    } else {
                        Toast.makeText(requireContext(), "API DATA for bahan is null", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<BahanTanamModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}
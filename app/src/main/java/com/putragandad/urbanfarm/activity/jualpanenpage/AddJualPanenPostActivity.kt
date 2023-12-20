package com.putragandad.urbanfarm.activity.jualpanenpage

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.api.ApiRetrofit
import com.putragandad.urbanfarm.databinding.ActivityAddJualPanenPostBinding
import com.putragandad.urbanfarm.models.api.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddJualPanenPostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddJualPanenPostBinding

    private val api by lazy { ApiRetrofit().endpoint }

    lateinit var etPostTitle: TextInputEditText
    lateinit var etPostContent: TextInputEditText
    lateinit var etContentImgUrl: TextInputEditText
    lateinit var etWhatsapp: TextInputEditText
    lateinit var kotaDropdown: AutoCompleteTextView
    lateinit var jenisTanamanDropdown: AutoCompleteTextView
    lateinit var metodeTanamDropdown: AutoCompleteTextView

    lateinit var getInputUsername: String
    lateinit var getInputIDUser: String
    lateinit var getInputProfileImgUrl: String
    lateinit var getInputPostTitle: String
    lateinit var getInputPostContent: String
    lateinit var getInputContentImgUrl: String
    lateinit var getInputWhatsapp: String
    lateinit var selectedKota: String
    lateinit var selectedJenisTanaman: String
    lateinit var selectedMetodeTanam: String

    lateinit var btnFabSave: FloatingActionButton

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddJualPanenPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setDropdown()
        setListener()
    }

    private fun setView() {
        // set binding
        etPostTitle = binding.etTitle
        etPostContent = binding.etContent
        etContentImgUrl = binding.etContentimgUrl
        etWhatsapp = binding.etWhatsapp

        kotaDropdown = binding.dropdownKota
        jenisTanamanDropdown = binding.dropdownJenisTanaman
        metodeTanamDropdown = binding.dropdownMetodeTanam

        btnFabSave = binding.fabSaveTambahTanamankuPage
    }

    private fun setDropdown() {
        // Connect string-array to dropdown
        val kotaDropdownItem = resources.getStringArray(R.array.list_kota)
        val jenisTanamanDropdownItem = resources.getStringArray(R.array.jenis_tanaman)
        val metodeTanamDropdownItem = resources.getStringArray(R.array.metode_tanam)

        val arrayKotaDropdownAdapter = ArrayAdapter(this, R.layout.dropdown_item_kota, kotaDropdownItem)
        kotaDropdown.setAdapter(arrayKotaDropdownAdapter)
        val arrayJenisTanamanDropdownAdapter = ArrayAdapter(this, R.layout.dropdown_item_jenis_tanaman, jenisTanamanDropdownItem)
        jenisTanamanDropdown.setAdapter(arrayJenisTanamanDropdownAdapter)
        val arrayMetodeTanamAdapter = ArrayAdapter(this, R.layout.dropdown_item_metode_tanam, metodeTanamDropdownItem)
        metodeTanamDropdown.setAdapter(arrayMetodeTanamAdapter)
    }

    private fun setData() {
        auth = FirebaseAuth.getInstance()

        val id_firebase = auth.currentUser?.uid
        val username = auth.currentUser?.displayName
        val profileImgUrl = auth.currentUser?.photoUrl

        getInputUsername = username.toString()
        getInputIDUser = id_firebase.toString()
        getInputContentImgUrl = etContentImgUrl.text.toString()
        getInputPostContent = etPostContent.text.toString()
        getInputWhatsapp = etWhatsapp.text.toString()
        getInputPostTitle = etPostTitle.text.toString()
        getInputProfileImgUrl = profileImgUrl.toString()
        selectedKota = kotaDropdown.text.toString()
        selectedMetodeTanam = metodeTanamDropdown.text.toString()
        selectedJenisTanaman = metodeTanamDropdown.text.toString()

//        kotaDropdown.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
//            selectedKota = kotaDropdown.adapter.getItem(position).toString()
//            Toast.makeText(this, selectedKota, Toast.LENGTH_LONG).show()
//        }
//
//        jenisTanamanDropdown.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
//            selectedJenisTanaman = jenisTanamanDropdown.adapter.getItem(position).toString()
//            Toast.makeText(this, selectedJenisTanaman, Toast.LENGTH_LONG).show()
//        }
//
//        metodeTanamDropdown.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
//            selectedMetodeTanam = metodeTanamDropdown.adapter.getItem(position).toString()
//            Toast.makeText(this, selectedMetodeTanam, Toast.LENGTH_LONG).show()
//        }
    }

    private fun isAnyFieldEmpty(): Boolean {
        return getInputPostTitle.isEmpty() ||
                getInputPostContent.isEmpty() ||
                getInputContentImgUrl.isEmpty() ||
                getInputWhatsapp.isEmpty() ||
                selectedKota.isEmpty() ||
                selectedJenisTanaman.isEmpty() ||
                selectedMetodeTanam.isEmpty()
    }

    private fun setListener() {
        btnFabSave.setOnClickListener {
            setData()
            if (!checkInternetConnection()) { // check internet connection
                Toast.makeText(this, "No internet connection. Please check your connection.", Toast.LENGTH_LONG).show()
            } else if (isAnyFieldEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_LONG).show()
            } else {
                createApiRequest()
                setResult(RESULT_OK)
                finish()
            }
        }
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun createApiRequest() {
        api.create(getInputIDUser,
            getInputProfileImgUrl,
            getInputUsername,
            getInputWhatsapp,
            getInputPostTitle,
            getInputPostContent,
            getInputContentImgUrl,
            selectedKota,
            selectedJenisTanaman,
            selectedMetodeTanam)
            .enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    if(response.isSuccessful) {
                        val result = response.body()
                        Toast.makeText(applicationContext, result!!.message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {

                }
            })
    }


}
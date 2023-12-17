package com.putragandad.urbanfarm.activity.tanamanku.detailtanamanpage

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.materialswitch.MaterialSwitch
import com.putragandad.urbanfarm.dao.tanamanku.TanamankuDao
import com.putragandad.urbanfarm.databinding.ActivityDetailTanamankuItemPolybagBinding
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels
import com.putragandad.urbanfarm.viewmodels.tanamanku.TanamankuViewModel
import java.util.UUID

class DetailTanamankuItemPolybagActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTanamankuItemPolybagBinding
    private var id : Int = 0
    private var tanamanId: String? = null
    private var amCekKondisiTanamanId: Int? = null

    private var alarmManager : AlarmManager? = null
    private lateinit var amSiramTanamanIntent : PendingIntent
    private lateinit var amCekKondisiTanamanIntent: PendingIntent

    private lateinit var switchSiramTanamanState : MaterialSwitch
    private lateinit var switchCekTanamanState: MaterialSwitch

    lateinit var viewModel: TanamankuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTanamankuItemPolybagBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TanamankuViewModel::class.java)

        val tanamankuListItem = intent.getParcelableExtra<TanamankuItemModels>("item_tanamanku_list")
        if(tanamankuListItem != null) {
            val imageTanamanku = binding.gambarTanamanku
            val titleTanamanku = binding.titleTanamanku
            val metodeTanamku = binding.tvMetodeTanam
            val jenisTanamanku = binding.tvJenisTanaman

            imageTanamanku.setImageResource(tanamankuListItem.fotoTanaman)
            titleTanamanku.text = tanamankuListItem.namaTanaman
            metodeTanamku.text = tanamankuListItem.metodeTanam
            jenisTanamanku.text = tanamankuListItem.jenisTanaman
            id = tanamankuListItem.id
            tanamanId = tanamankuListItem?.idTanaman

        //        Toast.makeText(this, "$id , $tanamanId", Toast.LENGTH_SHORT).show()
        //        Log.d("DetailTanamankuItemPolybagActivity", "ID: $id")
        }

        switchSiramTanamanState = binding.switchSiramTanaman
        switchCekTanamanState = binding.switchCekTanaman
        switchSiramTanamanState.isChecked = tanamankuListItem?.switchSiramTanaman == true
        switchCekTanamanState.isChecked = tanamankuListItem?.switchCekTanaman == true

        binding.switchSiramTanaman.setOnCheckedChangeListener { _, isChecked ->
            if (id != null) {
                if(isChecked) {
                    val updatedTanamankuListItem = tanamankuListItem?.copy(switchSiramTanaman = true) // update Switch to true
                    updatedTanamankuListItem?.id = id // set the id
//                    Log.d("Activity", "Updated item: $updatedTanamankuListItem")
                    if (updatedTanamankuListItem != null) {
                        viewModel.updateTanaman(updatedTanamankuListItem)
                    }
//                    Toast.makeText(this, "Switch on", Toast.LENGTH_SHORT).show()
                } else {
//                    Toast.makeText(this, "Switch off", Toast.LENGTH_SHORT).show()
                    val updatedTanamankuListItem = tanamankuListItem?.copy(switchSiramTanaman = false)
                    updatedTanamankuListItem?.id = id
                    if (updatedTanamankuListItem != null) {
                        viewModel.updateTanaman(updatedTanamankuListItem)
                    }
                }
            }
        }

        binding.switchCekTanaman.setOnCheckedChangeListener { _, isChecked ->
            if (id != null) {
                if(isChecked) {
                    val updatedTanamankuListItem = tanamankuListItem?.copy(switchCekTanaman = true) // update Switch to true
                    updatedTanamankuListItem?.id = id // set the id
//                    Log.d("Activity", "Updated item: $updatedTanamankuListItem")
                    if (updatedTanamankuListItem != null) {
                        viewModel.updateTanaman(updatedTanamankuListItem)
                    }
//                    Toast.makeText(this, "Switch on", Toast.LENGTH_SHORT).show()
                } else {
//                    Toast.makeText(this, "Switch off", Toast.LENGTH_SHORT).show()
                    val updatedTanamankuListItem = tanamankuListItem?.copy(switchCekTanaman = false)
                    updatedTanamankuListItem?.id = id
                    if (updatedTanamankuListItem != null) {
                        viewModel.updateTanaman(updatedTanamankuListItem)
                    }
                }
            }
        }
    }
}


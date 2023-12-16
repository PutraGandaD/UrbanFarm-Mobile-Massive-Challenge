package com.putragandad.urbanfarm.activity.tanamanku.detailtanamanpage

import android.app.AlarmManager
import android.app.PendingIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.putragandad.urbanfarm.databinding.ActivityDetailTanamankuItemPolybagBinding
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels

class DetailTanamankuItemPolybagActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTanamankuItemPolybagBinding
    private var id : Int? = 0
    private var tanamanId: String? = null

    private var alarmManager : AlarmManager? = null
    private lateinit var amSiramTanamanIntent : PendingIntent
    private lateinit var amCekKondisiTanamanIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTanamankuItemPolybagBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        }

        id = tanamankuListItem?.id
        tanamanId = tanamankuListItem?.idTanaman
        Toast.makeText(this, "$id , $tanamanId", Toast.LENGTH_SHORT).show()
        //Log.d("DetailTanamankuItemPolybagActivity", "ID: $id")
    }
}
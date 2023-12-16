package com.putragandad.urbanfarm.activity.tanamanku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.ActivityDetailTanamankuItemBinding
import com.putragandad.urbanfarm.models.tanamanku.TanamankuItemModels
import java.util.UUID

class DetailTanamankuItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTanamankuItemBinding
    private var id : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTanamankuItemBinding.inflate(layoutInflater)
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
        Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
        //Log.d("DetailTanamankuItemActivity", "ID: $id")
    }
}
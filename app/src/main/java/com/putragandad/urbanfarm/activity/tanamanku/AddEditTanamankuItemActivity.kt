package com.putragandad.urbanfarm.activity.tanamanku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.ActivityAddEditTanamankuItemBinding

class AddEditTanamankuItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditTanamankuItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditTanamankuItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jenisTanamanDropdownItem = resources.getStringArray(R.array.jenis_tanaman)
        val metodeTanamDropdownItem = resources.getStringArray(R.array.metode_tanam)

        val arrayJenisTanamanAdapter = ArrayAdapter(this, R.layout.dropdown_item_jenis_tanaman, jenisTanamanDropdownItem)
        binding.dropdownJenisTanaman.setAdapter(arrayJenisTanamanAdapter)

        val arrayMetodeTanamAdapter = ArrayAdapter(this, R.layout.dropdown_item_metode_tanam, metodeTanamDropdownItem)
        binding.dropdownMetodeTanam.setAdapter(arrayMetodeTanamAdapter)
    }
}
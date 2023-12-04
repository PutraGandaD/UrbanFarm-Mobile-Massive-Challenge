package com.putragandad.urbanfarm.activity.tanamanku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.ActivityAddEditTanamankuItemBinding
import com.putragandad.urbanfarm.viewmodels.tanamanku.TanamankuViewModel

class AddEditTanamankuItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditTanamankuItemBinding

    lateinit var namaTanamanEdt : TextInputEditText
    lateinit var jenisTanamanDropdown : AutoCompleteTextView
    lateinit var metodeTanamDropdown: AutoCompleteTextView
    lateinit var saveButton : FloatingActionButton

    lateinit var viewModel: TanamankuViewModel
    var tanamankuID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditTanamankuItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Connect ViewModels
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TanamankuViewModel::class.java)

        // Connect id from Add Edit layout
        namaTanamanEdt = binding.etNamaTanamanTanamanku
        jenisTanamanDropdown = binding.dropdownJenisTanaman
        metodeTanamDropdown = binding.dropdownMetodeTanam
        saveButton = binding.fabSaveTambahTanamankuPage

        // Connect string-array to dropdown
        val jenisTanamanDropdownItem = resources.getStringArray(R.array.jenis_tanaman)
        val metodeTanamDropdownItem = resources.getStringArray(R.array.metode_tanam)

        val arrayJenisTanamanAdapter = ArrayAdapter(this, R.layout.dropdown_item_jenis_tanaman, jenisTanamanDropdownItem)
        binding.dropdownJenisTanaman.setAdapter(arrayJenisTanamanAdapter)

        val arrayMetodeTanamAdapter = ArrayAdapter(this, R.layout.dropdown_item_metode_tanam, metodeTanamDropdownItem)
        binding.dropdownMetodeTanam.setAdapter(arrayMetodeTanamAdapter)

        jenisTanamanDropdown.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedJenisTanaman = jenisTanamanDropdown.adapter.getItem(position).toString()
            Toast.makeText(this, selectedJenisTanaman, Toast.LENGTH_LONG).show()
        }

        metodeTanamDropdown.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedMetodeTanam = metodeTanamDropdown.adapter.getItem(position).toString()
            Toast.makeText(this, selectedMetodeTanam, Toast.LENGTH_LONG).show()
        }

    }
}
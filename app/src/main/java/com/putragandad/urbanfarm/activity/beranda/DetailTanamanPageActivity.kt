package com.putragandad.urbanfarm.activity.beranda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.putragandad.urbanfarm.databinding.ActivityDetailTanamanPageBinding
import com.putragandad.urbanfarm.fragments.berandapage.modalbottomsheet.PilihMetodeTanamBottomSheetFragment
import com.putragandad.urbanfarm.models.beranda.ItemTanamanModels

class DetailTanamanPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTanamanPageBinding
    private lateinit var bottomSheetFragment: PilihMetodeTanamBottomSheetFragment
    private lateinit var getNamaTanaman: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTanamanPageBinding.inflate(layoutInflater)

        binding.TopAppBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setContentView(binding.root)

        val tanamanItem = intent.getParcelableExtra<ItemTanamanModels>("item_tanaman_dashboard")
        if(tanamanItem != null) {
            val imageTanaman = binding.imageCardDetailTanamanPage
            val namaTanaman = binding.tvTitleDetailTanamanPage
            val jenisTanaman = binding.tvJenisTanamanDetailTanamanPage
            val teknikMenanam = binding.tvTeknikMenanamDetailTanamanPage
            val tingkatKesulitanMenanam = binding.tvTingkatKesulitanDetailTanamanPage
            val deskripsiTanaman = binding.tvDeskripsiContentTanamankuPage

            imageTanaman.setImageResource(tanamanItem.fotoTanaman)
            namaTanaman.text = tanamanItem.namaTanaman
            jenisTanaman.text = tanamanItem.jenisTanaman
            teknikMenanam.text = tanamanItem.teknikMenanam
            tingkatKesulitanMenanam.text = tanamanItem.tingkatKesulitan
            deskripsiTanaman.text = tanamanItem.deskripsiTanaman

            getNamaTanaman = tanamanItem.namaTanaman
        }

        // send value to bottomsheet


        // add listener for starting bottomsheet
        binding.btnMulaiMenanam.setOnClickListener {
            bottomSheetFragment = PilihMetodeTanamBottomSheetFragment()
            sendStringtoBottomSheet()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }


    }

    private fun sendStringtoBottomSheet() {
        val bundle = Bundle()
        bundle.putString("namatanaman", getNamaTanaman)
        bottomSheetFragment.arguments = bundle
    }
}
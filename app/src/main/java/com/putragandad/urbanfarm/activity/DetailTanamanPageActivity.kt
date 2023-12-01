package com.putragandad.urbanfarm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.putragandad.urbanfarm.databinding.ActivityDetailTanamanPageBinding

class DetailTanamanPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTanamanPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTanamanPageBinding.inflate(layoutInflater)

        binding.TopAppBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setContentView(binding.root)
    }
}
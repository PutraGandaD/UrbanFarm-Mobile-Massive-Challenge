package com.putragandad.urbanfarm.activity.tanamanku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.ActivityDetailTanamankuItemBinding

class DetailTanamankuItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTanamankuItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTanamankuItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
package com.putragandad.urbanfarm.activity.profilpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.databinding.ActivityProfilEditPageBinding

class ProfilEditPageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProfilEditPageBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilEditPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val email = auth.currentUser?.email
        val name = auth.currentUser?.displayName
        val uid = auth.currentUser?.uid
        val profileImageUri = auth.currentUser?.photoUrl
        val profileImageUrl = profileImageUri.toString()

        binding.tvContentEmail.text = email
        binding.tvContentName.text = name
        binding.tvContentUid.text = uid
        binding.tvContentPhotourl.text = profileImageUrl
        Glide.with(this)
            .load(profileImageUri)
            .into(binding.ivAccount)
    }
}
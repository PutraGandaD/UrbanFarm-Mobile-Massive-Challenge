package com.putragandad.urbanfarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LandingPage4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page4)
    }

    fun onGoogleButtonClick(view: View) {
        // Pindah ke MainActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
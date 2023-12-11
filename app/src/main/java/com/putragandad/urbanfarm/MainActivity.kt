package com.putragandad.urbanfarm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.putragandad.urbanfarm.activity.landingpage.LandingPageActivity
import com.putragandad.urbanfarm.activity.onboarding.OnboardingActivity
import com.putragandad.urbanfarm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            val landingPageIntent = Intent(
                this@MainActivity,
                LandingPageActivity::class.java
            )

            val onBoardingPageIntent = Intent(
                this, OnboardingActivity::class.java)

            when(onBoardingFinished()) {
                true -> {
                    startActivity(landingPageIntent)
                    finish()
                }
                false -> {
                    startActivity(onBoardingPageIntent)
                    finish()
                }
            }

        }, 3000)
    }

    private fun onBoardingFinished() : Boolean {
        val sharedPref = this.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}
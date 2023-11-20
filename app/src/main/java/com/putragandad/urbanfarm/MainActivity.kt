package com.putragandad.urbanfarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.putragandad.urbanfarm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Beranda())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.beranda -> replaceFragment(Beranda())
                R.id.tanamanku -> replaceFragment(Tanamanku())
                R.id.jualBeliPanen -> replaceFragment(JualBeliPanen())
                R.id.profil -> replaceFragment(Profil())
                else ->{
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}
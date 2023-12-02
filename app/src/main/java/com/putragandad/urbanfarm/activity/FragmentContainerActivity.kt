package com.putragandad.urbanfarm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.adapters.dashboard.ItemTanamanAdapter
import com.putragandad.urbanfarm.databinding.ActivityFragmentContainerBinding
import com.putragandad.urbanfarm.fragments.berandapage.BerandaPageFragment
import com.putragandad.urbanfarm.fragments.jualpanenpage.JualPanenPageFragment
import com.putragandad.urbanfarm.fragments.profilpage.ProfilPageFragment
import com.putragandad.urbanfarm.fragments.tanamankupage.TanamankuPageFragment
import com.putragandad.urbanfarm.models.dashboard.ItemTanamanModels

class FragmentContainerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFragmentContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentContainerBinding.inflate(layoutInflater)

        setContentView(binding.root)

        replaceFragment(BerandaPageFragment())

        binding.bottomNavBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.beranda -> replaceFragment(BerandaPageFragment())
                R.id.tanamanku -> replaceFragment(TanamankuPageFragment())
                R.id.jualBeliPanen -> replaceFragment(JualPanenPageFragment())
                R.id.profil -> replaceFragment(ProfilPageFragment())
                else -> {

                }
            }

            true
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}
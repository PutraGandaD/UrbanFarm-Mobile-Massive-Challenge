package com.putragandad.urbanfarm.activity.tablayout_tanaman.hidroponik.panduantanam

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.adapters.onboarding.ViewPagerAdapter
import com.putragandad.urbanfarm.databinding.ActivityFirstStepFragmentHidroponikContainerBinding
import com.putragandad.urbanfarm.databinding.ActivityFirstStepFragmentPolybagContainerBinding
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.FirstScreen
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.FourthScreen
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.SecondScreen
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.ThirdScreen

class FirstStepFragmentHidroponikContainer : AppCompatActivity() {
    private lateinit var binding: ActivityFirstStepFragmentHidroponikContainerBinding
    private lateinit var viewPager: ViewPager2
    private var namaTanaman: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstStepFragmentHidroponikContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        namaTanaman = setNamaTanamanValue()

        val fragmentList: ArrayList<Fragment> = when(namaTanaman) {
            "bawangmerah" -> {
                arrayListOf(
                    com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.hidroponik.mediatanam.FirstScreen(),
                    com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.hidroponik.mediatanam.SecondScreen(),
                )
            }
            "cabairawit" -> {
                arrayListOf()
            }
            "buncis" -> {
                arrayListOf()
            }
            "wortel" -> {
                arrayListOf()
            }
            "kembangkol" -> {
                arrayListOf()
            }
            "tomat" -> {
                arrayListOf()
            }
            "kacangpanjang" -> {
                arrayListOf()
            }
            "timun" -> {
                arrayListOf()
            }
            else -> {
                arrayListOf()
            }
        }

        // Calling ViewPager id from Binding and set the adapter
        viewPager = binding.viewPagerPanduanTanamHidroponikFirstStep
        val adapter = ViewPagerAdapter(fragmentList, this.supportFragmentManager, lifecycle)

        viewPager.adapter = adapter
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle
            // the Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private fun setNamaTanamanValue() : String? {
        val sharedPreferences = getSharedPreferences("BottomSheetNamaTanaman",
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString("namaTanaman", "")
    }
}
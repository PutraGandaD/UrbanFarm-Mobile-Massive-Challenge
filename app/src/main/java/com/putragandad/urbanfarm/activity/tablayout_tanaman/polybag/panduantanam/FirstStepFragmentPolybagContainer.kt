package com.putragandad.urbanfarm.activity.tablayout_tanaman.polybag.panduantanam

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.putragandad.urbanfarm.adapters.onboarding.ViewPagerAdapter
import com.putragandad.urbanfarm.databinding.ActivityFirstStepFragmentPolybagContainerBinding
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.FirstScreen
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.FourthScreen
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.SecondScreen
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.ThirdScreen

class FirstStepFragmentPolybagContainer : AppCompatActivity() {
    private lateinit var binding: ActivityFirstStepFragmentPolybagContainerBinding
    private lateinit var viewPager: ViewPager2
    private var namaTanaman: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstStepFragmentPolybagContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        namaTanaman = setNamaTanamanValue()

        val fragmentList: ArrayList<Fragment> = when(namaTanaman) {
                "bawangmerah" -> {
                    arrayListOf(
                        FirstScreen(),
                        SecondScreen(),
                        ThirdScreen(),
                        FourthScreen()
                    )
                }
                "cabairawit" -> {
                    arrayListOf()
                }
                "buncis" -> {
                    arrayListOf(
                        com.putragandad.urbanfarm.fragments.panduantanam.buncis.polybag.mediatanam.FirstScreen(),
                        com.putragandad.urbanfarm.fragments.panduantanam.buncis.polybag.mediatanam.SecondScreen()
                    )
                }
                "wortel" -> {
                    arrayListOf(
                        com.putragandad.urbanfarm.fragments.panduantanam.wortel.polybag.mediatanam.FirstScreen(),
                        com.putragandad.urbanfarm.fragments.panduantanam.wortel.polybag.mediatanam.SecondScreen(),
                        com.putragandad.urbanfarm.fragments.panduantanam.wortel.polybag.mediatanam.ThirdScreen(),
                    )
                }
                "kembangkol" -> {
                    arrayListOf()
                }
                "tomat" -> {
                    arrayListOf(
                        com.putragandad.urbanfarm.fragments.panduantanam.tomat.polybag.mediatanam.FirstScreen(),
                        com.putragandad.urbanfarm.fragments.panduantanam.tomat.polybag.mediatanam.SecondScreen(),
                    )
                }
                "kacangpanjang" -> {
                    arrayListOf(
                        com.putragandad.urbanfarm.fragments.panduantanam.kacangpanjang.polybag.mediatanam.FirstScreen(),
                        com.putragandad.urbanfarm.fragments.panduantanam.kacangpanjang.polybag.mediatanam.SecondScreen(),
                    )
                }
                "timun" -> {
                    arrayListOf(
                        com.putragandad.urbanfarm.fragments.panduantanam.timun.polybag.mediatanam.FirstScreen(),
                        com.putragandad.urbanfarm.fragments.panduantanam.timun.polybag.mediatanam.SecondScreen(),
                    )
                }
                else -> {
                    arrayListOf()
                }
            }

        // Calling ViewPager id from Binding and set the adapter
        viewPager = binding.viewPagerPanduanTanamPolybagFirstStep
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
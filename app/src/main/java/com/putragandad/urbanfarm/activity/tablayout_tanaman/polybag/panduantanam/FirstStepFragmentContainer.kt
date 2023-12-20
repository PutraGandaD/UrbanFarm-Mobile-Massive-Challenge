package com.putragandad.urbanfarm.activity.tablayout_tanaman.polybag.panduantanam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.putragandad.urbanfarm.adapters.onboarding.ViewPagerAdapter
import com.putragandad.urbanfarm.databinding.ActivityPanduanTanamFirstStepFragmentContainerBinding
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.FirstScreen
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.FourthScreen
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.SecondScreen
import com.putragandad.urbanfarm.fragments.panduantanam.bawangmerah.polybag.mediatanam.ThirdScreen

class FirstStepFragmentContainer : AppCompatActivity() {
    private lateinit var binding: ActivityPanduanTanamFirstStepFragmentContainerBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanduanTanamFirstStepFragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen(),
            FourthScreen()
        )

        // Calling ViewPager id from Binding and set the adapter
        viewPager = binding.viewPagerPanduanTanamFirstStep
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
}
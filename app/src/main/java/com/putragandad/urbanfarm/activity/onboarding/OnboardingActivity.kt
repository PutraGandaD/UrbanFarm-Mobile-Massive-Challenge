package com.putragandad.urbanfarm.activity.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.putragandad.urbanfarm.fragments.onboarding.screens.FirstScreen
import com.putragandad.urbanfarm.fragments.onboarding.screens.SecondScreen
import com.putragandad.urbanfarm.fragments.onboarding.screens.ThirdScreen
import com.putragandad.urbanfarm.adapters.onboarding.ViewPagerAdapter
import com.putragandad.urbanfarm.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        viewPager = binding.viewPager
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
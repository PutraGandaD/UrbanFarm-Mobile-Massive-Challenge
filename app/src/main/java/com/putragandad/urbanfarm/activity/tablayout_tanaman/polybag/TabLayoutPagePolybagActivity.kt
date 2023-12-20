package com.putragandad.urbanfarm.activity.tablayout_tanaman.polybag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.adapters.panduantanam.ViewPagerPanduanTanamAdapter

class TabLayoutPagePolybagActivity : AppCompatActivity() {
    val tabTitle = arrayOf("Alat dan Bahan", "Panduan Tanam", "Perawatan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panduan_tanam_page_polybag)

        var pager = findViewById<ViewPager2>(R.id.viewPager_panduan)
        var tl = findViewById<TabLayout>(R.id.tabLayout_panduan)
        pager.adapter = ViewPagerPanduanTanamAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tl, pager) {
            tab, position ->
                tab.text = tabTitle[position]
        }.attach()
    }
}
package com.putragandad.urbanfarm.activity.tablayout_tanaman.hidroponik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.adapters.tablayout_tanaman_adapter.tablayout_viewpager.hidroponik.ViewPagerPanduanTanamHidroponikAdapter

class TabLayoutPageHidroponikActivity : AppCompatActivity() {
    val tabTitle = arrayOf("Alat dan Bahan", "Panduan Tanam", "Perawatan")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_page_hidroponik)

        var pager = findViewById<ViewPager2>(R.id.viewPager_panduan_hidroponik)
        var tl = findViewById<TabLayout>(R.id.tabLayout_panduan_hidroponik)
        pager.adapter = ViewPagerPanduanTanamHidroponikAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tl, pager) {
            tab, position ->
                tab.text = tabTitle[position]
        }.attach()

    }
}
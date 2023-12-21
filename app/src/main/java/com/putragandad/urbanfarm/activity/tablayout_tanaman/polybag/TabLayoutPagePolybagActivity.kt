package com.putragandad.urbanfarm.activity.tablayout_tanaman.polybag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.putragandad.urbanfarm.R
import com.putragandad.urbanfarm.adapters.tablayout_tanaman_adapter.tablayout_viewpager.polybag.ViewPagerPanduanTanamPolybagAdapter

class TabLayoutPagePolybagActivity : AppCompatActivity() {
    val tabTitle = arrayOf("Alat dan Bahan", "Panduan Tanam", "Perawatan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_page_polybag)

        var pager = findViewById<ViewPager2>(R.id.viewPager_panduan_polybag)
        var tl = findViewById<TabLayout>(R.id.tabLayout_panduan_polybag)
        pager.adapter = ViewPagerPanduanTanamPolybagAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tl, pager) {
            tab, position ->
                tab.text = tabTitle[position]
        }.attach()
    }
}
package com.putragandad.urbanfarm.adapters.panduantanam

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.putragandad.urbanfarm.fragments.panduantanam.AlatDanBahanFragment
import com.putragandad.urbanfarm.fragments.panduantanam.PanduanTanamFragment
import com.putragandad.urbanfarm.fragments.panduantanam.PerawatanPageFragment

class ViewPagerPanduanTanamAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return AlatDanBahanFragment()
            1 -> return PanduanTanamFragment()
            2 -> return PerawatanPageFragment()
            else -> return AlatDanBahanFragment()
        }
    }

}
package com.putragandad.urbanfarm.adapters.tablayout_tanaman_adapter.tablayout_viewpager.polybag

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.putragandad.urbanfarm.fragments.panduantanam.tablayout.polybag.AlatDanBahanPolybagFragment
import com.putragandad.urbanfarm.fragments.panduantanam.tablayout.polybag.PanduanTanamPolybagFragment
import com.putragandad.urbanfarm.fragments.panduantanam.tablayout.polybag.PerawatanPagePolybagFragment

class ViewPagerPanduanTanamPolybagAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return AlatDanBahanPolybagFragment()
            1 -> return PanduanTanamPolybagFragment()
            2 -> return PerawatanPagePolybagFragment()
            else -> return AlatDanBahanPolybagFragment()
        }
    }

}
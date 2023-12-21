package com.putragandad.urbanfarm.adapters.tablayout_tanaman_adapter.tablayout_viewpager.hidroponik

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.putragandad.urbanfarm.fragments.panduantanam.tablayout.hidroponik.AlatDanBahanHidroponikFragment
import com.putragandad.urbanfarm.fragments.panduantanam.tablayout.hidroponik.PanduanTanamHidroponikFragment
import com.putragandad.urbanfarm.fragments.panduantanam.tablayout.hidroponik.PerawatanPageHidroponikFragment

class ViewPagerPanduanTanamHidroponikAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return AlatDanBahanHidroponikFragment()
            1 -> return PanduanTanamHidroponikFragment()
            2 -> return PerawatanPageHidroponikFragment()
            else -> return AlatDanBahanHidroponikFragment()
        }
    }


}
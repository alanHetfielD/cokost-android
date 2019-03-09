package com.madukubah.pariwisata.view.item.banner.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.madukubah.cokost.BuildConfig
import com.madukubah.cokost.model.Ads
import com.madukubah.pariwisata.view.item.banner.BannerFragment

class BannerAdapter(fragmentManager: FragmentManager,
                    private val banners: List<Ads>) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(pos: Int): Fragment {
        return BannerFragment.newInstance(BuildConfig.IKLAN_IMAGE + banners[pos].advertise_photo)
    }
    override fun getCount(): Int = banners.size
}
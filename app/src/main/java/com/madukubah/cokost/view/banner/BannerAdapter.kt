package com.madukubah.cokost.view.banner

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.madukubah.cokost.model.Ads

class BannerAdapter(fragmentManager: FragmentManager,
                    private val banners: List<Ads>
                    ) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(pos: Int): Fragment {
//        return BannerFragment.newInstance(banners[pos].advertise_photo)
        return BannerFragment.newInstance(banners[pos].advertise_photo)
    }

    override fun getCount(): Int = banners.size

//    fun add( ads: Ads)
//    {
//        banners.add( ads )
//    }

//    private var banners: MutableList<Ads> = mutableListOf()
//    private  val fragments = ArrayList<Fragment>()
}
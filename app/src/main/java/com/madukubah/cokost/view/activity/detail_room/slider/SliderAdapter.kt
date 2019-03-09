package com.madukubah.cokost.view.activity.detail_room.slider

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SliderAdapter( manager: FragmentManager)
    : FragmentStatePagerAdapter(manager)
{
    override fun getItem(position: Int): Fragment = fragments[ position ]

    override fun getCount():  Int = fragments.size
    fun addFragment( fragment: Fragment)
    {
        fragments.add( fragment )
    }
    private  val fragments = ArrayList<Fragment>()
}
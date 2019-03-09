package com.madukubah.cokost.view.activity.main

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.madukubah.cokost.BuildConfig
import com.madukubah.cokost.BuildConfig.MOBILE_VERSION
import com.madukubah.cokost.R
import com.madukubah.cokost.model.Mobile
import com.madukubah.cokost.model.response.AdsResponse
import com.madukubah.cokost.model.response.MobileResponse
import com.madukubah.cokost.view.kost.KostPresenter
import com.madukubah.cokost.view.kost.KostView
import com.madukubah.cokost.view.kost.ListFragment
import com.madukubah.cokost.view.room.RoomFragment
import com.madukubah.cokost.view.simpan.SimpanFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.*

class MainActivity
    :
        AppCompatActivity(),
        KostView.MainView,
        BottomNavigationView.OnNavigationItemSelectedListener,
        AnkoLogger
{

    override fun loadMobileVersion(response: MobileResponse) {
        val mobile : Mobile = response.Mobiles[0]
        if( mobile.mobile_version != MOBILE_VERSION)
        {
            val al = alert("Mohon update aplikasi Co-Kost keversi yang terbaru") {
                title = "Update"
                yesButton {
//                    toast("Yess!!!")
                    startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.madukubah.cokost" )
                            )
                    )
                    finish()
                }
                noButton {
                    finish()
                }
            }.build()
            al.setCancelable(false)
            al.setCanceledOnTouchOutside(false)
            al.show()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        info("nav id : " + item.itemId)
//        kostPresenter.BottomNavMenuSelected( item.itemId )
        val id = item.itemId
        when(id){
            R.id.navigation_kost->{
                manager.beginTransaction().hide(active).show(kostFragment).commit()
                active = kostFragment
                return true
            }
            R.id.navigation_room->{
                manager.beginTransaction().hide(active).show(roomFragment).commit()
                active = roomFragment
                return true
            }
            R.id.navigation_favorite ->{
                manager.beginTransaction().hide(active).show(simpanFragment).commit()
                active = simpanFragment
                return true
            }
        }
        return false
    }

    override fun setPresenter(presenter: KostView.MainPresenter) {

    }


    override fun selectFragment(fragment: Fragment) {
//        val ft = supportFragmentManager.beginTransaction()
//        ft.replace( R.id.frame, fragment )
//        ft.commit()
    }

    private  lateinit var kostPresenter : KostPresenter

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kostPresenter = KostPresenter( this )
        kostPresenter.start()

        manager.beginTransaction().add(R.id.frame,  roomFragment ).hide( roomFragment).commit()
        manager.beginTransaction().add(R.id.frame,  simpanFragment ).hide( simpanFragment).commit()
        manager.beginTransaction().add(R.id.frame,  kostFragment ).commit()

        navigation.setOnNavigationItemSelectedListener(this)

    }

    val manager = supportFragmentManager
    val kostFragment : ListFragment = ListFragment.create()
    val roomFragment : RoomFragment = RoomFragment.create()
    val simpanFragment : SimpanFragment = SimpanFragment.create()
    var active : Fragment = kostFragment
}

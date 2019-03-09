package com.madukubah.cokost.view.activity.detail_room

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.bumptech.glide.Glide
import com.madukubah.cokost.BuildConfig
import com.madukubah.cokost.R
import com.madukubah.cokost.config.base.Config
import com.madukubah.cokost.model.Room
import com.madukubah.cokost.view.activity.detail_room.slider.SliderAdapter
import com.madukubah.cokost.view.activity.detail_room.slider.SliderFragment
import com.madukubah.cokost.view.room.HubungiFragment
import com.madukubah.cokost.view.room.detail.RoomDetailPresenter
import com.madukubah.cokost.view.room.detail.RoomDetailView
import kotlinx.android.synthetic.main.activity_detail_room.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast



class DetailRoomActivity
    :
        AppCompatActivity(),
        RoomDetailView.View,
        AnkoLogger
{
    override fun showMessage(message: String) {
        toast( message )
    }

    override fun updateFavoriteLayout(isFavorite: Boolean) {
        info { "isFavorite = " + isFavorite }
        if( isFavorite )
            btn_simpan.text = "Buang"
        else
            btn_simpan.text = "Simpan"
    }

    override fun loadData(room: Room) {
//        Glide.with(this)
//                .load(BuildConfig.KAMAR_IMAGE+ room.kamar_foto)
//                .into(img_photo)
        tv_harga.text = "Rp. "+ Config.priceFormat( room.kamar_harga )
        tv_quantity.text = "Tersedia "+ room.kamar_quantity + " kamar"
        tv_facilities.text = Config.convert( room.kamar_facility )
        tv_size.text = room.kamar_panjang + " x " + room.kamar_lebar + " m"
        tv_time.text = "1 Tahun"
        tv_owner.text= room.user_profile_fullname.capitalize() + " ( " + room.kost_name + " ) "
        tv_kost_address.text = room.kost_address.capitalize()
        tv_has_been_seen.text = "Dilihat "+ room.kamar_hit + " Kali"

        val images = room.kamar_foto.split(";")

        setupViewPager( viewPager_slider , images )
    }
    override fun setPresenter(presenter: RoomDetailView.Presenter) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_room)

        room = intent.getParcelableExtra(ROOM)
        roomDetailPresenter = RoomDetailPresenter(room, this)
        roomDetailPresenter.start()


        btn_hubungi.onClick {
            val hubungiFragment : HubungiFragment = HubungiFragment(){
                when( it ){
                    0->{
//                        toast("telepon")
                        val dialPhoneIntent = Intent( Intent.ACTION_DIAL, Uri.parse("tel:" + room.user_profile_phone ))
                        startActivity(dialPhoneIntent)
                    }
                    1->{
//                        toast("SMS")
                        sendSMS()
                    }
                }
            }
            val ft = getSupportFragmentManager().beginTransaction()
            hubungiFragment.show(ft , "doalog")
        }

        btn_simpan.onClick {
            roomDetailPresenter.onSimpanButtonPressed()
        }
    }

    private fun setupViewPager(pager : ViewPager, Images : List<String>)
    {
        val adapter = SliderAdapter( supportFragmentManager )

        Images.map {
            adapter.addFragment( SliderFragment.newInstance(BuildConfig.KAMAR_IMAGE + it ) )
        }

        pager?.adapter = adapter
    }

    private fun sendSMS()
    {   val tx = "betul ini dgn pemilik kost " + room.kost_name + " ? saya tertarik dengan kamar tipe " + room.kamar_type + " dan saya ingin menyewanya \n\n Sms Via Co-Kost App"
        val i = Intent( Intent.ACTION_VIEW, Uri.fromParts("sms", "" + room.user_profile_phone  , this.toString()) )
        i.putExtra("sms_body", tx );

        try {
            startActivity( i )
            finish()
            toast("buka sms ")
        } catch (e: Exception) {
            toast("gagal mengirim sms")
        }
    }

    companion object {
        const val ROOM = "ROOM"
    }

    lateinit var  room : Room
    lateinit var  roomDetailPresenter : RoomDetailPresenter

}

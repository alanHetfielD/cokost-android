package com.madukubah.cokost.view.activity.detail_kost

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.madukubah.cokost.BuildConfig
import com.madukubah.cokost.R
import com.madukubah.cokost.config.base.invisible
import com.madukubah.cokost.config.base.visible
import com.madukubah.cokost.model.Kost
import com.madukubah.cokost.model.response.KostResponse
import com.madukubah.cokost.view.room.RoomPresenter
import com.madukubah.cokost.view.room.RoomPresenterAnko
import com.madukubah.cokost.view.room.RoomView
import kotlinx.android.synthetic.main.activity_detail_kost.*

class DetailKostActivity
    : AppCompatActivity(),
        RoomView.RoomView
{
    override fun emptyMore(empty: Boolean) {

    }

    override fun showLoading() {
        progressBar_detail_kost.visible()
    }

    override fun hideLoading() {
        progressBar_detail_kost.invisible()
    }

    override fun onLoadKost(response: KostResponse) {
        val kost : Kost = response.Kosts[0]
        Glide.with(this)
            .load(BuildConfig.KOST_IMAGE+ kost.kost_photo)
            .into(img_item_photo)
        tv_item_name.text = kost.kost_name.capitalize()
        tv_kost_pemilik.text = "Pemilik : "+  kost.user_profile_fullname.capitalize()
        tv_kost_alamat.text = kost.kost_address.capitalize()
        tv_kost_keterangan.text = kost.kost_keterangan
    }

    override fun empty(bool: Boolean, length: Int) {
        tv_kamar_jenis.text = "Tersedia "+ length + " Jenis kamar "
    }

    override fun setPresenter(presenter: RoomView.RoomPresenter) {
        roomPresenter = presenter as RoomPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kost)

        tv_item_name.text = ""
        tv_kost_pemilik.text = ""
        tv_kost_alamat.text = ""
        tv_kost_keterangan.text = ""

        kost_id = intent.getLongExtra(KOST, 0 )
        roomPresenter = RoomPresenterAnko( this , this)
        roomPresenter.start()

        recycler_kamar.layoutManager = LinearLayoutManager( this )
        recycler_kamar.adapter = roomPresenter.adapter
        recycler_kamar.isNestedScrollingEnabled = false
        recycler_kamar.setHasFixedSize(true)

        (roomPresenter as RoomPresenterAnko).loadDataByKostId( kost_id )
        (roomPresenter as RoomPresenterAnko).loadKostByKostId( kost_id )

    }

    companion object {
        const val KOST = "KOST"
    }

    lateinit var kost : Kost
    var kost_id : Long = 0
    lateinit var roomPresenter : RoomPresenter
}

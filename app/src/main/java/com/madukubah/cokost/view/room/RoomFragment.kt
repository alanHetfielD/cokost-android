package com.madukubah.cokost.view.room


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.madukubah.cokost.R
import com.madukubah.cokost.config.base.InfiniteScrollListener
import com.madukubah.cokost.config.base.invisible
import com.madukubah.cokost.config.base.visible
import com.madukubah.cokost.model.response.KostResponse
import com.madukubah.cokost.view.room.RoomView.RoomPresenter
import kotlinx.android.synthetic.main.fragment_room.*
import kotlinx.android.synthetic.main.fragment_room.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.onRefresh


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RoomFragment
    : Fragment(),
        RoomView.RoomView,
        AnkoLogger
{
    override fun emptyMore(empty: Boolean) {
        if( empty )
            btn_load_more_room.invisible()
        else
            btn_load_more_room.visible()
    }

    override fun showLoading() {
        btn_load_more_room.isEnabled = false
        info { "Loading show" }
        progressBar2.visible()
        swipeRefreshLayout2.isRefreshing = false
    }

    override fun hideLoading() {
        btn_load_more_room.isEnabled = true
        info { "Loading hide" }
        progressBar2.invisible()
    }

    override fun onLoadKost(response: KostResponse) {
    }

    override fun empty(bool: Boolean, length: Int) {
        if( bool )
            tv_check_empty_room.visible()
        else
            tv_check_empty_room.invisible()
    }

    override fun setPresenter(presenter: RoomPresenter) {
        roomPresenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_room, container, false)
        roomPresenter = RoomPresenterAnko( this, checkNotNull((this as Fragment).context) )
        roomPresenter.start()

        view.recycler.layoutManager = LinearLayoutManager( context )
        view.recycler.adapter = (roomPresenter as RoomPresenterAnko).adapter
        view.recycler.isNestedScrollingEnabled = false
        view.recycler.setHasFixedSize(true)
        view.btn_load_more_room.onClick {
            (roomPresenter as RoomPresenterAnko).loadMoreA()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        roomPresenter.loadData()
        swipeRefreshLayout2.onRefresh {
            roomPresenter.loadData()
        }

        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar( toolbar_layout_room )
        }
        toolbar_layout_room.setTitleTextColor( resources.getColor(R.color.colorWhite) )
    }

    companion object {
        fun create() : RoomFragment
        {
            val fragment = RoomFragment()
            return fragment
        }
    }

    lateinit var roomPresenter : RoomPresenter
}

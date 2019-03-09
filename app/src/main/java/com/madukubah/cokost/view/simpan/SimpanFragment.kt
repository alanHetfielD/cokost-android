package com.madukubah.cokost.view.simpan


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*

import com.madukubah.cokost.R
import com.madukubah.cokost.config.base.invisible
import com.madukubah.cokost.config.base.visible
import com.madukubah.cokost.view.activity.tentang.TentangActivity
import kotlinx.android.synthetic.main.fragment_simpan.*
import kotlinx.android.synthetic.main.fragment_simpan.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SimpanFragment
    :
        Fragment(),
        SimpanView.View
{
    override fun empty(bool: Boolean) {
        if( bool )
            tv_check_empty_simpan.visible()
        else
            tv_check_empty_simpan.invisible()
    }

    override fun showLoading() {
        swipeRefreshLayout3.isRefreshing = false
        progressBar_simpan.visible()

    }

    override fun hideLoading() {
        progressBar_simpan.invisible()
    }

    override fun setPresenter(presenter: SimpanView.Presenter) {
        simpanPresenter = presenter as SimpanPresenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_simpan, container, false)
        simpanPresenter = SimpanPresenterAnko( this )

        simpanPresenter.start()

        view.recycler_simpan.layoutManager = LinearLayoutManager(context)
        view.recycler_simpan.adapter = simpanPresenter.adapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (simpanPresenter as SimpanPresenterAnko).loadFromDatabase()

        swipeRefreshLayout3.onRefresh {
            (simpanPresenter as SimpanPresenterAnko).loadFromDatabase()
        }
        setHasOptionsMenu(true)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar( toolbar_layout_simpan )
        }
        toolbar_layout_simpan.setTitleTextColor( resources.getColor(R.color.colorWhite) )
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.tentang, menu )
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.add_tentang ->{
                val ctx = checkNotNull((this as Fragment).context)
                ctx.startActivity<TentangActivity>()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun create() : SimpanFragment
        {
            val fragment = SimpanFragment()
            return fragment
        }
    }

    lateinit var simpanPresenter : SimpanPresenter
}

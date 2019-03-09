package com.madukubah.cokost.view.kost


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.bumptech.glide.Glide
import com.madukubah.cokost.BuildConfig

import com.madukubah.cokost.R
import com.madukubah.cokost.R.id.progressBar
import com.madukubah.cokost.R.id.swipeRefreshLayout
import com.madukubah.cokost.config.base.InfiniteScrollListener
import com.madukubah.cokost.config.base.invisible
import com.madukubah.cokost.config.base.visible
import com.madukubah.cokost.model.Ads
import com.madukubah.cokost.model.response.AdsResponse
import com.madukubah.cokost.view.banner.BannerAdapter
import com.madukubah.cokost.view.banner.BannerFragment
import com.madukubah.pariwisata.view.item.banner.BannerCarouselItem
import com.madukubah.pariwisata.view.item.banner.BannerListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.backgroundColorResource
import org.jetbrains.anko.info
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListFragment
    : Fragment(),
        KostView.ListView,
        AnkoLogger,
        BannerListener
{
    override fun onSeeAllPromoClick() {
        toast("see all promo")
    }

    override fun onBannerClick(promo: Ads) {

    }

    override fun emptyMore(empty: Boolean) {
        if( empty )
            btn_load_more.invisible()
        else
            btn_load_more.visible()
    }

    override fun loadAdvertisement(response: AdsResponse) {
        if( response.ADs.size <= 0 )
        {
            return
        }
        setAds(response.ADs)
    }

    override fun showLoading() {
        btn_load_more.isEnabled = false
        info { "Loading show" }
        progressBar.visible()
        swipeRefreshLayout.isRefreshing = false
    }

    override fun hideLoading() {
        btn_load_more.isEnabled = true
        info { "Loading hide" }
        progressBar.invisible()
    }

    override fun empty(bool: Boolean) {
        if( bool )
        {
            tv_check_empty.visible()
        }else{
            tv_check_empty.invisible()
        }
    }

    override fun setPresenter(presenter: KostView.ListPresenter) {
        listPresenter = presenter as ListPresenter
    }

    companion object {
    fun create() : ListFragment
    {
        val fragment = ListFragment()
        return fragment
    }
}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        listPresenter = ListPresenterAnko( this )
        listPresenter.start( )

        view.recycler.layoutManager = LinearLayoutManager( context )
        view.recycler.adapter = listPresenter.adapter
        view.recycler.isNestedScrollingEnabled = false
        view.recycler.setHasFixedSize(true)
        view.btn_load_more.onClick {
            (listPresenter as ListPresenterAnko).loadMoreA()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ( listPresenter as ListPresenterAnko ).loadData()
        swipeRefreshLayout.onRefresh {
            ( listPresenter as ListPresenterAnko ).loadData()
        }
        setHasOptionsMenu(true)
        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).setSupportActionBar( toolbar_layout )
        }
        toolbar_layout.setTitleTextColor( resources.getColor(R.color.colorWhite) )

//        val promos = listOf(
//                Ads(
//                        advertise_id = 12,
//                        advertise_photo = "https://s2.bukalapak.com/uploads/promo_partnerinfo_bloggy/2842/Bloggy_1_puncak.jpg",
//                        advertise_desc = ""
//                ),
//                Ads(
//                        advertise_id = 12,
//                        advertise_photo = "https://s4.bukalapak.com/uploads/promo_partnerinfo_bloggy/5042/Bloggy_1.jpg",
//                        advertise_desc = ""
//                ),
//                Ads(
//                        advertise_id = 12,
//                        advertise_photo = "https://s2.bukalapak.com/uploads/promo_partnerinfo_bloggy/2842/Bloggy_1_puncak.jpg",
//                        advertise_desc = ""
//                ),
//                Ads(
//                        advertise_id = 12,
//                        advertise_photo = "https://s4.bukalapak.com/uploads/promo_partnerinfo_bloggy/5042/Bloggy_1.jpg",
//                        advertise_desc = ""
//                ),
//                Ads(
//                        advertise_id = 12,
//                        advertise_photo = "https://s2.bukalapak.com/uploads/promo_partnerinfo_bloggy/2842/Bloggy_1_puncak.jpg",
//                        advertise_desc = ""
//                ),
//                Ads(
//                        advertise_id = 12,
//                        advertise_photo = "https://s4.bukalapak.com/uploads/promo_partnerinfo_bloggy/5042/Bloggy_1.jpg",
//                        advertise_desc = ""
//                )
//        )
        rvBanner.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
        }
//        val fm = getFragmentManager()
//        val bannerCarouselItem = BannerCarouselItem(promos, fm, this)
//        groupAdapter.add(bannerCarouselItem)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.search, menu )
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.backgroundColorResource = R.color.colorPrimary
        searchView.setQueryHint(getString(R.string.example_search_kost))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                info("kost query = "+ query)
                if (query != null) {
                    ( listPresenter as ListPresenterAnko ).searchKost( query )
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                info("kost query = "+ query)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    fun setAds(ads : List<Ads>)
    {
        val fm = getFragmentManager()
        val bannerCarouselItem = BannerCarouselItem(ads, fm, this)
        groupAdapter.add(bannerCarouselItem)
    }

    lateinit var listPresenter: ListPresenter
//    val adapter = fragmentManager?.let {
//        BannerAdapter(it)
//    }
    private var groupAdapter = GroupAdapter<ViewHolder>()
//    private lateinit var bannerCarouselItem : BannerCarouselItem
//    var promos : MutableList<Ads> = mutableListOf()
}

package com.madukubah.cokost.config.base

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class InfiniteScrollListener
(
        val Loadmore: () -> Unit,
        val layoutManager: LinearLayoutManager
)
    :
    RecyclerView.OnScrollListener(),
        AnkoLogger
{
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        var visibleItemCount = recyclerView?.childCount
        var totalItemCount = recyclerView?.layoutManager?.itemCount
        var firstVisibleItem = (recyclerView?.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()

        info { "mPreviousTotal = "+ mPreviousTotal + ";visibleItemCount = "+visibleItemCount+"; totalItemCount = "+totalItemCount+ ";firstVisibleItem = "+firstVisibleItem+"; mLoading = "+ mLoading}

        if (mLoading) {
            if (totalItemCount != null) {
//                if( totalItemCount < mPreviousTotal ){
//                    mPreviousTotal = 0
//                    mLoading = true
//                    info {"back to top reload"}
//                }

                if (totalItemCount > mPreviousTotal) {
                    mLoading = false
                    mPreviousTotal = totalItemCount
                }
            }
        }
        val visibleThreshold = 0
        if (totalItemCount != null) {
            if (  !mLoading && ( ( totalItemCount - visibleItemCount!!  ) <=  (  firstVisibleItem + visibleThreshold ) )  )
            {
                info {"reload = "+ totalItemCount}
                onLoadMore()
                mLoading = true
            }
        }
    }

    fun onLoadMore()
    {
        Loadmore()
    }

    private var mPreviousTotal = 0
    private var mLoading = true
}
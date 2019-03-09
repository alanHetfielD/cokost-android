package com.madukubah.cokost.view.room


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.madukubah.cokost.R
import kotlinx.android.synthetic.main.fragment_hubungi.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@SuppressLint("ValidFragment")
/**
 * A simple [Fragment] subclass.
 *
 */
class HubungiFragment(
        private val listener : (code : Int) -> Unit
)
    :
        DialogFragment(),
        View.OnClickListener
{
    override fun onClick(p0: View?) {
        if (p0 != null) {
            when( p0.id ){
                R.id.btn_telp ->
                    listener(TELP)
                R.id.btn_sms ->
                    listener(SMS)
            }
        }

        dialog.dismiss()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hubungi, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_telp.setOnClickListener(this)
        btn_sms.setOnClickListener(this)
    }

    companion object {
        const val TELP = 0
        const val SMS = 1
    }

}

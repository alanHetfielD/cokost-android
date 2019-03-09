package com.madukubah.cokost.config.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.text.DecimalFormat

object Config {
    val gson : Gson
        get () = GsonBuilder()
//                .setDateFormat("yyyy-MM-dd")
                .create()

    fun priceFormat( price :String ) : String
    {
        val l = price.length
        val formatter = DecimalFormat("#,###")
        val myNumber = price.toBigDecimal()
        val formattedNumber = formatter.format(myNumber)
        return formattedNumber
    }

    fun convert(value : String?) : String?
    {
        return value?.replace(";","\n")
    }
}
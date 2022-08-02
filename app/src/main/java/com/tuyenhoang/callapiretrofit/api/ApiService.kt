package com.tuyenhoang.callapiretrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    //https://land2107.herokuapp.com/image/4k
    private var retrofit: Retrofit?=null
    val inter:Interface
    get() {
        if (retrofit==null){
            retrofit=Retrofit.Builder().baseUrl("https://land2107.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit!!.create(Interface::class.java)
    }

}
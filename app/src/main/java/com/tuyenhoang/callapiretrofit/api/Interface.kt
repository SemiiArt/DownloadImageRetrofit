package com.tuyenhoang.callapiretrofit.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface Interface {
    @GET("image/4k")
    fun getImages():Call<List<String?>?>?
}
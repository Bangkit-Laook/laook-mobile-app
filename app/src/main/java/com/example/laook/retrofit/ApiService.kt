package com.example.laook.retrofit

import com.example.laook.response.Menu
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("suggest_menus")
    fun getMenu(@Query("menus") menuId: String): Call<List<Menu>>
}


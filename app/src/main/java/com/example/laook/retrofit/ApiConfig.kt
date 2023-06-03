package com.example.laook.retrofit

import com.example.laook.response.ApiResponse
import com.example.laook.response.Menu
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiConfig {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://647b3a6ad2e5b6101db1014a.mockapi.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getMenu(menuId: String, callback: Callback<List<Menu>>) {
        val call = apiService.getMenu(menuId)
        call.enqueue(callback)
    }
}
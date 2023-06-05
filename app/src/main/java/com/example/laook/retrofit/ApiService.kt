package com.example.laook.retrofit

import com.example.laook.response.Menu
import com.example.laook.response.MenuResponse
import com.example.laook.response.SuggestMenusRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("suggest_menus")
    suspend fun suggestMenus(@Body request: SuggestMenusRequest): Response<MenuResponse>
}
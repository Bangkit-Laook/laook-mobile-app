package com.example.laook.retrofit

import com.example.laook.response.MenuResponse
import com.example.laook.response.ScanResponse
import com.example.laook.response.SuggestMenusRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("suggest_menus")
    suspend fun suggestMenus(@Body request: SuggestMenusRequest): Response<MenuResponse>

    @Multipart
    @POST("recognize_ingredients")
    fun uploadImage(@Part image: MultipartBody.Part): Call<ScanResponse>
}


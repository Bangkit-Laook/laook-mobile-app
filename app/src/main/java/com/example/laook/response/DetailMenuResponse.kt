package com.example.laook.response

import com.google.gson.annotations.SerializedName

data class MenuResponse(
    val menus: List<Menu>
)

data class Menu(
    val name: String?=null,
    val description: String?=null,
    val image_url: String?=null,
    val ingredients: List<String>,
    val steps: List<String>?=null
)




data class SuggestMenusRequest(val ingredients: List<String>)




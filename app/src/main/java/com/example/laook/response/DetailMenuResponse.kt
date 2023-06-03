package com.example.laook.response

import com.google.gson.annotations.SerializedName


data class DetailMenuResponse(
    @field:SerializedName("name")
    val name:String,

    @field:SerializedName("description")
    val description:String,

    @field:SerializedName("image_url")
    val image_url:String,

    @field:SerializedName("ingredients")
    val ingredients:String,

    @field:SerializedName("listIngredient")
    val listIngredient: List<ListIngredientsResponse>,

    @field:SerializedName("steps")
    val steps: List<StepsResponse>
)

data class ListIngredientsResponse(
    @field:SerializedName("ingredients")
    val ingredients:String
)

data class StepsResponse(
    @field:SerializedName("steps")
    val steps:String
)

data class Menu(
    val name: String,
    val description: String,
    val image_url: String,
    val ingredients: List<String>,
    val steps: List<String>,
    val menus: String
)

data class ApiResponse<T>(
    val data: T?,
    val error: String?
)

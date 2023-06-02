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


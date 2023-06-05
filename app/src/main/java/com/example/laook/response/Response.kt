package com.example.laook.response

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("menus")
	val menus: List<MenusItem>
)

data class MenusItem(

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("ingredients")
	val ingredients: List<String>,

	@field:SerializedName("steps")
	val steps: List<String>
)

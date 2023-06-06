package com.example.laook.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MenuResponse(
    val menus: List<Menu>
)

@Parcelize
data class Menu(
    val name: String?=null,
    val description: String?=null,
    val image_url: String?=null,
    val ingredients: List<String>,
    val steps: List<String>
) : Parcelable




data class SuggestMenusRequest(val ingredients: List<String>)

data class ScanResponse(
    val ingredients: List<String>
)

data class ErrorResponse(
    val detail: List<ErrorDetailItem>
)

data class ErrorDetailItem(
    val msg: String,
    val loc: List<String>,
    val type: String
)



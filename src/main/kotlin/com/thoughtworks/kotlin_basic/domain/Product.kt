package com.thoughtworks.kotlin_basic.domain

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("SKU")
    val sku: String,
    @SerializedName("price")
    val price: Float,
    @SerializedName("type")
    val type: String,
    @SerializedName("image")
    val image: String,
)

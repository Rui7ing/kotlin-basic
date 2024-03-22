package com.thoughtworks.kotlin_basic.domain

import com.google.gson.annotations.SerializedName

data class Inventory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("SKU")
    val sku: String,
    @SerializedName("zone")
    val zone: String,
    @SerializedName("quantity")
    val quantity: Long,
)

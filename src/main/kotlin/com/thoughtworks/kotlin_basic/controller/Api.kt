package com.thoughtworks.kotlin_basic.controller

import com.thoughtworks.kotlin_basic.domain.Inventory
import com.thoughtworks.kotlin_basic.domain.Product
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/products")
    fun listProducts(): Call<List<Product>>

    @GET("/inventories")
    fun listInventories(): Call<List<Inventory>>
}

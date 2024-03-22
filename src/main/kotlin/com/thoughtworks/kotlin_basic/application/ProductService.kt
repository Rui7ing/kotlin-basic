package com.thoughtworks.kotlin_basic.application

import com.thoughtworks.kotlin_basic.controller.ApiClient.apiService
import com.thoughtworks.kotlin_basic.domain.Inventory
import com.thoughtworks.kotlin_basic.domain.Product
import com.thoughtworks.kotlin_basic.dto.ProductDto

class ProductService {

    fun getProductList(): List<Product> = apiService.listProducts().execute().body() ?: emptyList()

    fun getInventoriesList(): List<Inventory> = apiService.listInventories().execute().body() ?: emptyList()

    fun getProductDtoList(products: List<Product>, inventories: List<Inventory>): List<ProductDto> {
        val dtos = products.map { ProductDto(it) }
        inventories.forEach { inventory ->
            dtos.forEach {
                it.updateQuantity(inventory)
                it.updateZone(inventory)
                it.updateShowPrice()
            }
        }
        return dtos
    }
}

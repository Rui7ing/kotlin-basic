package com.thoughtworks.kotlin_basic.controller

import com.thoughtworks.kotlin_basic.controller.ApiClient.apiService
import com.thoughtworks.kotlin_basic.domain.Product

class ProductService {

    fun getProductList(): List<Product>? {
        return apiService.listProducts().execute().body()
    }
}

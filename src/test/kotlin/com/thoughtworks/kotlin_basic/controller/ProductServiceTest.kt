package com.thoughtworks.kotlin_basic.controller;

import com.thoughtworks.kotlin_basic.domain.Product
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ProductServiceTest {

    private val productService = mockk<ProductService>()
    private val mockProduct = listOf(
        Product(id = 1, name = "Electronic Watch", sku = "ABC123", price = 299.99f, type = "NORMAL", image = "image1.jpg"),
        Product(id = 2, name = "Sports Shoes", sku = "DEF456", price = 499.6f, type = "NORMAL", image = "image2.jpg"),
        Product(id = 3, name = "Book \"Exploring the Universe\"", sku = "GHI789", price = 89f, type = "NORMAL", image = "image3.jpg"),
        Product(id = 4, name = "Bluetooth Headphones", sku = "JKL012", price = 199f, type = "HIGH_DEMAND", image = "image4.jpg"),
        Product(id = 5, name = "Smart Band", sku = "MNO345", price = 149.45f, type = "HIGH_DEMAND", image = "image5.jpg")
    )

    @Test
    fun testGetProductList() {
        every { productService.getProductList() } returns mockProduct
        val result = productService.getProductList();
        Assertions.assertEquals(5, result?.size);
    }
}

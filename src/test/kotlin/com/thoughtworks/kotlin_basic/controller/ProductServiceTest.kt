package com.thoughtworks.kotlin_basic.controller;

import com.thoughtworks.kotlin_basic.application.ProductService
import com.thoughtworks.kotlin_basic.domain.Inventory
import com.thoughtworks.kotlin_basic.domain.Product
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProductServiceTest {

    @MockK
    private var mockService = ProductService()
    private var products = emptyList<Product>()
    private var inventories = emptyList<Inventory>()

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        every { mockService.getInventoriesList() } returns mockInventories
        every { mockService.getProductList() } returns mockProducts
        products = mockService.getProductList()
        inventories = mockService.getInventoriesList()
    }

    private val mockProducts = listOf(
        Product(
            id = 1,
            name = "Electronic Watch",
            sku = "ABC123",
            price = 299.99f,
            type = "NORMAL",
            image = "image1.jpg"
        ),
        Product(id = 2, name = "Sports Shoes", sku = "DEF456", price = 499.6f, type = "NORMAL", image = "image2.jpg"),
        Product(
            id = 3,
            name = "Book \"Exploring the Universe\"",
            sku = "GHI789",
            price = 89f,
            type = "NORMAL",
            image = "image3.jpg"
        ),
        Product(
            id = 4,
            name = "Bluetooth Headphones",
            sku = "JKL012",
            price = 199f,
            type = "HIGH_DEMAND",
            image = "image4.jpg"
        ),
        Product(
            id = 5,
            name = "Smart Band",
            sku = "MNO345",
            price = 149.45f,
            type = "HIGH_DEMAND",
            image = "image5.jpg"
        )
    )

    private val mockInventories = listOf(
        Inventory(id = 1, sku = "ABC123", zone = "CN_NORTH", quantity = 22),
        Inventory(id = 2, sku = "ABC123", zone = "US_WEST", quantity = 8),
        Inventory(id = 3, sku = "DEF456", zone = "EU_CENTRAL", quantity = 200),
        Inventory(id = 4, sku = "GHI789", zone = "CN_EAST", quantity = 150),
        Inventory(id = 5, sku = "JKL012", zone = "CN_SOUTH", quantity = 17),
        Inventory(id = 6, sku = "JKL012", zone = "US_EAST", quantity = 13),
        Inventory(id = 7, sku = "MNO345", zone = "CN_NORTH", quantity = 55),
        Inventory(id = 8, sku = "MNO345", zone = "AU_SOUTH", quantity = 30),
    )

    @Test
    fun `should get product list`() {
        every { mockService.getProductList() } returns mockProducts
        val result = mockService.getProductList();
        Assertions.assertEquals(5, result.size);
    }

    @Test
    fun `should get inventory list`() {
        every { mockService.getInventoriesList() } returns mockInventories
        val result = mockService.getInventoriesList();
        Assertions.assertEquals(8, result.size);
    }

    @Test
    fun `price should not change when type is NORMAL`() {
        val dtos = ProductService().getProductDtoList(products, inventories)
        val dto = dtos.filter { it.sku == "ABC123" }.get(0)
        Assertions.assertTrue(dto.type == "NORMAL");
        Assertions.assertTrue(dto.quantity <= 30);
        Assertions.assertEquals(dto.originalPrice, dto.showPrice);
    }

    @Test
    fun `price should change correctly when quantity less than or is 30`() {
        val dtos = ProductService().getProductDtoList(products, inventories)
        val dto = dtos.filter { it.sku == "JKL012" }.get(0)
        Assertions.assertTrue(dto.type == "HIGH_DEMAND");
        Assertions.assertTrue(dto.quantity <= 30)
        Assertions.assertEquals(dto.originalPrice * 1.5f, dto.showPrice);
    }

    @Test
    fun `price should change correctly when quantity more than 30 and less than 100`() {

        val dtos = ProductService().getProductDtoList(products, inventories)
        val dto = dtos.filter { it.sku == "MNO345" }.get(0)
        Assertions.assertTrue(dto.type == "HIGH_DEMAND")
        Assertions.assertTrue(dto.quantity in 31..100)
        Assertions.assertEquals(dto.originalPrice * 1.2f, dto.showPrice);
    }
}

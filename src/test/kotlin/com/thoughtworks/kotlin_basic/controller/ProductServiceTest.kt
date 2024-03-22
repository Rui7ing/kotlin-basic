package com.thoughtworks.kotlin_basic.controller;

import com.thoughtworks.kotlin_basic.MockData
import com.thoughtworks.kotlin_basic.application.ProductService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProductServiceTest : MockData{

    @MockK
    private var mockService = ProductService()
    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        every { mockService.getProductList() } returns getMockProducts()
        every { mockService.getInventoriesList() } returns getMockInventories()
    }

    @Test
    fun `should get product list`() {
        val result = mockService.getProductList();
        Assertions.assertEquals(5, result.size);
    }

    @Test
    fun `should get inventory list`() {
        val result = mockService.getInventoriesList();
        Assertions.assertEquals(8, result.size);
    }

    @Test
    fun `price should not change when type is NORMAL`() {
        val dtos = ProductService().getProductDtoList(mockService.getProductList(), mockService.getInventoriesList())
        val dto = dtos.filter { it.sku == "ABC123" }.get(0)
        Assertions.assertTrue(dto.type == "NORMAL");
        Assertions.assertTrue(dto.quantity <= 30);
        Assertions.assertEquals(dto.originalPrice, dto.showPrice);
    }

    @Test
    fun `price should change correctly when quantity less than or is 30`() {
        val dtos = ProductService().getProductDtoList(mockService.getProductList(), mockService.getInventoriesList())
        val dto = dtos.filter { it.sku == "JKL012" }.get(0)
        Assertions.assertTrue(dto.type == "HIGH_DEMAND");
        Assertions.assertTrue(dto.quantity <= 30)
        Assertions.assertEquals(dto.originalPrice * 1.5f, dto.showPrice);
    }

    @Test
    fun `price should change correctly when quantity more than 30 and less than 100`() {

        val dtos = ProductService().getProductDtoList(mockService.getProductList(), mockService.getInventoriesList())
        val dto = dtos.filter { it.sku == "MNO345" }.get(0)
        Assertions.assertTrue(dto.type == "HIGH_DEMAND")
        Assertions.assertTrue(dto.quantity in 31..100)
        Assertions.assertEquals(dto.originalPrice * 1.2f, dto.showPrice);
    }
}

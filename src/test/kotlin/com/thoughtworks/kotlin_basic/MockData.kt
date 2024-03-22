package com.thoughtworks.kotlin_basic

import com.thoughtworks.kotlin_basic.domain.Inventory
import com.thoughtworks.kotlin_basic.domain.Product

interface MockData {
    fun getMockProducts() = listOf(
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

    fun getMockInventories() = listOf(
        Inventory(id = 1, sku = "ABC123", zone = "CN_NORTH", quantity = 22),
        Inventory(id = 2, sku = "ABC123", zone = "US_WEST", quantity = 8),
        Inventory(id = 3, sku = "DEF456", zone = "EU_CENTRAL", quantity = 200),
        Inventory(id = 4, sku = "GHI789", zone = "CN_EAST", quantity = 150),
        Inventory(id = 5, sku = "JKL012", zone = "CN_SOUTH", quantity = 17),
        Inventory(id = 6, sku = "JKL012", zone = "US_EAST", quantity = 13),
        Inventory(id = 7, sku = "MNO345", zone = "CN_NORTH", quantity = 55),
        Inventory(id = 8, sku = "MNO345", zone = "AU_SOUTH", quantity = 30),
    )
}

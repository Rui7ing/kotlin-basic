package com.thoughtworks.kotlin_basic.dto

import com.thoughtworks.kotlin_basic.domain.Inventory
import com.thoughtworks.kotlin_basic.domain.Product

data class ProductDto(val product: Product) {
    val id: Int = product.id
    val name: String = product.name
    val sku: String = product.sku
    val originalPrice: Float = product.price
    var showPrice: Float = product.price
    val type: String = product.type
    val image: String = product.image
    var quantity: Long = 0
    var zone: String = ""

    fun updateQuantity(inventory: Inventory) {
        if (inventory.sku == this.sku) {
            this.quantity += inventory.quantity
        }
    }

    fun updateZone(inventory: Inventory) {
        if (inventory.sku == this.sku) {
            this.zone = "${this.zone}\\${inventory.zone}"
        }
    }

    fun updateShowPrice() {
        if (this.type == "NORMAL") { return }
        when (this.quantity) {
            in 0L..30L -> this.showPrice = originalPrice * 1.5f
            in 31L..100L -> this.showPrice = originalPrice * 1.2f
            else -> this.showPrice = originalPrice
        }
    }

    fun convertToList(): List<String> {
        return listOf(
            this.id.toString(),
            this.name,
            this.sku,
            this.quantity.toString(),
            this.originalPrice.toString(),
            this.showPrice.toString(),
            this.zone,
            this.type,
            this.image)
    }

}

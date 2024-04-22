package com.stu26172.store.products.domain.model


data class Product(
    val id: Int = 0,
    val title: String = "",
    val price: Double = 0.0,
    val category: String = "",
    val description: String = "",
    val image: String = ""
)

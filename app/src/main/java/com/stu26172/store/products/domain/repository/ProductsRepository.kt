package com.stu26172.store.products.domain.repository

import arrow.core.Either
import com.stu26172.store.products.domain.model.NetworkError
import com.stu26172.store.products.domain.model.Product

interface ProductsRepository {
    suspend fun getProducts(): Either<NetworkError, List<Product>>
    suspend fun getProductCategories(): List<String>
    suspend fun getProductById(id: Int): Product
}
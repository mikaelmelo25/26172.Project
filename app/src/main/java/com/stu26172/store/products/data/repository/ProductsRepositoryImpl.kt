package com.stu26172.store.products.data.repository

import arrow.core.Either
import com.stu26172.store.products.data.mapper.toNetworkError
import com.stu26172.store.products.data.remote.ProductsApiService
import com.stu26172.store.products.domain.model.NetworkError
import com.stu26172.store.products.domain.model.Product
import com.stu26172.store.products.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApiService: ProductsApiService
): ProductsRepository {
    override suspend fun getProducts(): Either<NetworkError, List<Product>> {
        return  Either.catch {
            productsApiService.getProducts()
        }.mapLeft { it.toNetworkError() }
    }

    override suspend fun getProductCategories(): List<String> {
       return listOf("All") + productsApiService.getProductCategories()
    }

    override suspend fun getProductById(id: Int): Product {
        return productsApiService.getProduct(id)
    }
}
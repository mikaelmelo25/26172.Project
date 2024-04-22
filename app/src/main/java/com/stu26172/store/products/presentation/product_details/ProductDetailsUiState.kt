package com.stu26172.store.products.presentation.product_details

import com.stu26172.store.products.domain.model.Product

data class ProductDetailsUiState(
    val product: Product = Product(),
    val isLoading: Boolean = true
)

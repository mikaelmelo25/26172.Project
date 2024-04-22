package com.stu26172.store.products.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stu26172.store.cart.domain.model.ProductInfo
import com.stu26172.store.cart.domain.repository.CartRepository
import com.stu26172.store.cart.presentation.cart_screen.CartViewModel
import com.stu26172.store.products.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getProducts()
        getProductCategories()
    }

    fun getProducts(category: String = "All") {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            productsRepository.getProducts().onRight { products ->
                val filteredProducts = when (category) {
                    "All" -> {
                        products
                    }

                    else -> {
                        products.filter { product ->
                            product.category == category
                        }
                    }
                }.sortedBy { product -> product.title }
                _state.update {
                    it.copy(products = filteredProducts, categorySelected = category)
                }

            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun getProductCategories() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            _state.update {
                it.copy(productCategories = productsRepository.getProductCategories())
            }
            _state.update { it.copy(isLoading = false) }

        }
    }

    fun onQueryChange(text: String) {
        viewModelScope.launch {
            val products = _state.value.products
            _state.update { it ->
                it.copy(products = when {
                    text.isNotEmpty() -> products.filter { product ->
                        product.title.contains(text.trim(), ignoreCase = true)
                    }.sortedBy { product -> product.title }

                    else -> products
                })
            }

        }
    }

    fun addProductToCart(id: Int) {
        viewModelScope.launch {
            cartRepository.getCart().onRight { carts ->

                val wantedCart = carts.find { it.id == 0 }

                wantedCart?.let { cart ->
                    val newProductInfo = ProductInfo(
                        id, 1
                    )
                    val products = listOf(newProductInfo) + cart.products
                    cartRepository.upsert(
                        cart.copy(
                            products = products
                        )
                    )
                }
            }
        }
    }

}
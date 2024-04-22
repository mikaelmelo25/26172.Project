package com.stu26172.store.cart.domain.repository

import arrow.core.Either
import com.stu26172.store.cart.domain.model.Cart
import com.stu26172.store.core.utils.Constants
import com.stu26172.store.products.domain.model.NetworkError


interface CartRepository {

   suspend fun getCart(userId: Int = Constants.DEFAULT_USER_ID): Either<NetworkError, List<Cart>>

   suspend fun delete(cart: Cart)

   suspend fun upsert(cart: Cart)
}
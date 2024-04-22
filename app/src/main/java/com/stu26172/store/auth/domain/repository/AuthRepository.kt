package com.stu26172.store.auth.domain.repository

import arrow.core.Either
import com.stu26172.store.auth.domain.model.LoginRequest
import com.stu26172.store.auth.domain.model.LoginResponse
import com.stu26172.store.products.domain.model.NetworkError

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Either<NetworkError, LoginResponse>
}
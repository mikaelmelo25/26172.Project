package com.stu26172.store.auth.data.repository

import arrow.core.Either
import com.stu26172.store.auth.data.remote.AuthApiService
import com.stu26172.store.auth.domain.model.LoginRequest
import com.stu26172.store.auth.domain.model.LoginResponse
import com.stu26172.store.auth.domain.repository.AuthRepository
import com.stu26172.store.products.data.mapper.toNetworkError
import com.stu26172.store.products.domain.model.NetworkError
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
): AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Either<NetworkError, LoginResponse> {
        return Either.catch {
            authApiService.login(loginRequest)
        }.mapLeft {
            it.toNetworkError()
        }
    }
}
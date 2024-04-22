package com.stu26172.store.auth.presentation.auth_screen

import com.stu26172.store.products.domain.model.ApiError

data class AuthUiState(
    val error: ApiError? = null,
    val loginIsSuccessful: Boolean = false,
    val isLoading: Boolean = false,
)
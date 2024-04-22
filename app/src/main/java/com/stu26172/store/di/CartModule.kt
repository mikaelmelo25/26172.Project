package com.stu26172.store.di

import com.stu26172.store.cart.data.local.datasource.CartDao
import com.stu26172.store.cart.data.remote.CartApiService
import com.stu26172.store.cart.data.repository.CartRepositoryImpl
import com.stu26172.store.cart.domain.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CartModule {

    @Singleton
    @Provides
    fun provideCartApiService(): CartApiService {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CartApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCartRepository(cartApiService: CartApiService, cartDao: CartDao): CartRepository {
        return CartRepositoryImpl(cartApiService, cartDao)
    }
}
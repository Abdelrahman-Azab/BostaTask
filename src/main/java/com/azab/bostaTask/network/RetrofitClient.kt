package com.azab.bostaTask.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Need only one Instance and is Not Known Class we need to inject it by module and Provides
@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {
    private const val BASE_URL="https://jsonplaceholder.typicode.com/"

    @Singleton
    @Provides
    fun RetrofitProvide():Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
    @Singleton
    @Provides
    fun ApiProvide(retrofit: Retrofit):ApiInterface
    {
        return retrofit.create(ApiInterface::class.java)
    }


}
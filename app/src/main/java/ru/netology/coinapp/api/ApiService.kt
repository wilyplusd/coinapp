package ru.netology.coinapp.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import ru.netology.coinapp.BuildConfig
import ru.netology.coinapp.dto.Asset

private const val BASE_URL = BuildConfig.API_BASE_URL

fun retrofit(): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("assets")
    suspend fun getAssets(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 2000
    ): Response<ApiResponse<List<Asset>>>
}

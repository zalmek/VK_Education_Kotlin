package com.example.vk_education.Network

import com.example.vk_education.Model.Data
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {
    @GET("v1/gifs/search")
    suspend fun getGifs(@Query("api_key") api_key: String, @Query("q") query: String): Data

    companion object {
        fun create(): GifApi {
            val BASE_URL = "https://api.giphy.com/"


            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder().addNetworkInterceptor(HttpLoggingInterceptor().apply {
                        level =
                            HttpLoggingInterceptor.Level.BODY
                    }).build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GifApi::class.java)
        }
    }
}
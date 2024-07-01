package com.example.akilisawa.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    companion object {
        private const val BASE_URL = "https://api.dev.malipo.org/"

        private val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS) // Set the connect timeout to 30 seconds
            .readTimeout(30, TimeUnit.SECONDS) // Set the read timeout to 30 seconds
            .writeTimeout(30, TimeUnit.SECONDS) // Set the write timeout to 30 seconds
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
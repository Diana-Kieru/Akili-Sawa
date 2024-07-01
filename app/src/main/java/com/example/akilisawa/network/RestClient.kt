package com.example.akilisawa.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {

    companion object {
        private const val BASE_URL = "https://api.dev.malipo.org/"
        private const val CLIENT_ID = "swaggerClientId"
        private const val CLIENT_SECRET = "secret"
        private var accessToken: String = ""
        private var refreshToken: String = ""

        private val authInterceptor = Interceptor { chain ->
            val original: Request = chain.request()

            val requestBuilder: Request.Builder = original.newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .method(original.method(), original.body())

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        private val tokenRefreshInterceptor = Interceptor { chain ->
            val response: Response = chain.proceed(chain.request())

            if (response.code() == 401) {
                // Get a new access token using the refresh token
                val newAccessToken = refreshAccessToken(refreshToken)

                // Retry the request with the new access token
                val newRequest = chain.request().newBuilder()
                    .header("Authorization", "Bearer $newAccessToken")
                    .build()

                chain.proceed(newRequest)
            } else {
                response
            }
        }

        private fun refreshAccessToken(refreshToken: String): String {
            // Make a network call to your server's token endpoint with the refresh token
            // and client credentials to get a new access token.
            // This is a simplified example, you'll need to implement the actual network call.
            return "newAccessToken"
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(tokenRefreshInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
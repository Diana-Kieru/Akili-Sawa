package com.example.akilisawa.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request

class AuthInterceptor : Interceptor {

    companion object {
        private const val CLIENT_ID = "swaggerClientId"
        private const val CLIENT_SECRET = "secret"
    }

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original: Request = chain.request()

        val requestBuilder: Request.Builder = original.newBuilder()
            .header("Authorization", Credentials.basic(CLIENT_ID, CLIENT_SECRET))
            .method(original.method(), original.body())

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}
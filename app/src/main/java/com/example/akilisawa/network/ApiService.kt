package com.example.akilisawa.network

import com.example.akilisawa.model.ClientTokenResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("auth/oauth/token")
    fun getClientToken(
        @Field("grant_type") grantType: String?,
        @Field("username") phoneNumber: String? = null,
        @Field("password") password: String? = null,
    ): Call<ClientTokenResponse>
}
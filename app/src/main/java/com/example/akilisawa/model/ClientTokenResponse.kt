package com.example.akilisawa.model

data class ClientTokenResponse(
    val access_token: String,
    val refresh_token: String,
    val token_type: String,
    val expires_in: Int,
    val scope: String,
    val jti: String,
    val user_id: String?,
    val email: String?,
    val name: String?,
)
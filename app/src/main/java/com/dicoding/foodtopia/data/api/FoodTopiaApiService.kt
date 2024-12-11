package com.dicoding.foodtopia.data.api

import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface FoodTopiaApiService {

    @GET("api/recipes/random")
    suspend fun getRandomRecipes(@Query("count") count: Int): Response<RandomRecipesResponse>

    @GET("api/recipes/search")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<RandomRecipesResponse>

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @GET("api/auth/user")
    suspend fun getUserProfile(@Header("Authorization") token: String): Response<UserResponse>
}

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val message: String,
    val token: String
)

data class UserResponse(
    val name: String,
    val email: String
)

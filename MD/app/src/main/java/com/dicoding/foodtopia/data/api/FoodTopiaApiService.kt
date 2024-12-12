package com.dicoding.foodtopia.data.api

import com.dicoding.foodtopia.data.modelrecipe.RandomRecipesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
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

    @POST("api/favorite")
    suspend fun addToFavorites(
        @Header("Authorization") token: String,
        @Body request: AddFavoriteRequest
    ): Response<FavoriteResponse>

    @GET("api/favorite")
    suspend fun getFavorites(
        @Header("Authorization") token: String
    ): Response<FavoritesResponse>

    @DELETE("api/favorite/{recipeId}")
    suspend fun removeFavorite(
        @Header("Authorization") token: String,
        @Path("recipeId") recipeId: String
    ): Response<FavoriteResponse>
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
    val token: String,
    val expiresIn: Long = 3600 // Default 1 hour if not provided by server
)

data class UserResponse(
    val name: String,
    val email: String
)

data class AddFavoriteRequest(
    val RecipeId: String
)

data class FavoriteResponse(
    val message: String
)

data class FavoritesResponse(
    val favorites: List<RandomRecipesResponse.RecipesItem>
)

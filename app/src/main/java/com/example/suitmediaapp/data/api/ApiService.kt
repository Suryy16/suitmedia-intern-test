package com.example.suitmediaapp.data.api

import com.example.suitmediaapp.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * ApiService is an interface that defines the API endpoints for the application.
 * It uses Retrofit annotations to specify the HTTP methods and request parameters.
 * The getUsers function retrieves a list of users from the specified page and perPage parameters.
 */
interface ApiService {
    /**
     * Retrieves a list of users from the API.
     *
     * @param page The page number to retrieve.
     * @param perPage The number of users per page.
     * @return A UserResponse object containing the list of users.
     */
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UserResponse
}
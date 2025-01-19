package com.example.suitmediaapp.repository

import com.example.suitmediaapp.data.api.ApiClient

/**
 * UserRepository is a class that provides methods to interact with the API for user-related data.
 * It uses the ApiClient to make network requests and retrieve user data.
 */
class UserRepository {
    private val api = ApiClient.apiService

    /**
     * Retrieves a list of users from the API.
     *
     * @param page The page number to retrieve.
     * @param perPage The number of users per page.
     * @return A UserResponse object containing the list of users.
     */
    suspend fun getUsers(page: Int, perPage: Int) = api.getUsers(page, perPage)
}
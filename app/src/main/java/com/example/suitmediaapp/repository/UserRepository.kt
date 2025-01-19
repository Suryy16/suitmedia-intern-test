package com.example.suitmediaapp.repository

import com.example.suitmediaapp.data.api.ApiClient

class UserRepository {
    private val api = ApiClient.apiService

    suspend fun getUsers(page: Int, perPage: Int) = api.getUsers(page, perPage)
}
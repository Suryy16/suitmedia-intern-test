package com.example.suitmediaapp.model

data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

data class UserResponse(
    val data: List<User>,
    val page: Int,
    val total_pages: Int
)
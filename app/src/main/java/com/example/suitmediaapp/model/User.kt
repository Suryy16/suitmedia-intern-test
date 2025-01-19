package com.example.suitmediaapp.model

/**
 * User is a data class that represents a user in the application.
 *
 * @param id The unique identifier of the user.
 * @param email The email address of the user.
 * @param first_name The first name of the user.
 * @param last_name The last name of the user.
 * @param avatar The URL of the user's avatar image.
 */
data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

/**
 * UserResponse is a data class that represents the response from the API
 * when retrieving a list of users.
 *
 * @param data The list of users.
 * @param page The current page number.
 * @param total_pages The total number of pages available.
 */
data class UserResponse(
    val data: List<User>,
    val page: Int,
    val total_pages: Int
)
package com.example.suitmediaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediaapp.model.User
import com.example.suitmediaapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * UserViewModel is a ViewModel class that manages the user-related data and operations.
 * It interacts with the UserRepository to fetch and update user data.
 */
class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private var currentPage = 1
    private val perPage = 20

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    init {
        fetchUsers() // Ensure users are fetched when ViewModel is created
    }

    /**
     * Fetches the list of users from the repository.
     */
    fun fetchUsers() {
        viewModelScope.launch {
            val response = repository.getUsers(currentPage, perPage)
            response?.let {
                _users.value = it.data
            }
        }
    }

    /**
     * Refreshes the list of users by resetting the current page and fetching the first page of users.
     */
    fun refreshUsers() {
        viewModelScope.launch {
            _isRefreshing.value = true
            currentPage = 1
            val response = repository.getUsers(currentPage, perPage)
            response?.let {
                _users.value = it.data
            }
            _isRefreshing.value = false
        }
    }

    /**
     * Loads the next page of users and appends them to the current list.
     */
    fun loadNextPage() {
        viewModelScope.launch {
            currentPage++
            val response = repository.getUsers(currentPage, perPage)
            response?.let {
                _users.value = _users.value + it.data
            }
        }
    }

    /**
     * Selects a user and updates the selectedUser state.
     *
     * @param user The user to be selected.
     */
    fun selectUser(user: User) {
        _selectedUser.value = user
    }

    /**
     * Updates the name state with the new name.
     *
     * @param newName The new name to be updated.
     */
    fun updateName(newName: String) {
        _name.value = newName
    }
}
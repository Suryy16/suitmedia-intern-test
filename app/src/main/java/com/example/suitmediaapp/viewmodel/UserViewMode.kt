package com.example.suitmediaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediaapp.model.User
import com.example.suitmediaapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser

    private var currentPage = 1

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    init {
        fetchUsers() // Ensure users are fetched when ViewModel is created
    }

    fun fetchUsers() {
        viewModelScope.launch {
            val response = repository.getUsers(currentPage, 10)
            response?.let {
                _users.value = it.data
            }
        }
    }

    fun selectUser(user: User) {
        _selectedUser.value = user
    }

    fun updateName(newName: String) {
        _name.value = newName
    }
}
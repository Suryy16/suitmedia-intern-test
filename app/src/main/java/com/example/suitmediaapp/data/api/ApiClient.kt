package com.example.suitmediaapp.data.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ApiClient is an object that provides a singleton instance of the ApiService.
 * It uses Retrofit to create the ApiService with a base URL and a Gson converter factory.
 * The ApiService is used to make network requests to the specified base URL.
 */

object ApiClient {
    private const val BASE_URL = "https://reqres.in/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
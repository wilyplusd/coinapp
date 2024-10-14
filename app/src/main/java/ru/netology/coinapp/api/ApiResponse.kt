package ru.netology.coinapp.api

data class ApiResponse<T> (
    val data: T,
    val timestamp: String,
)
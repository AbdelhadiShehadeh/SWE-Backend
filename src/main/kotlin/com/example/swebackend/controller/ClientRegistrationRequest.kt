package com.example.swebackend.controller

data class ClientRegistrationRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val amount: Double
)

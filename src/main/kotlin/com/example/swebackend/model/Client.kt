package com.example.swebackend.model

import jakarta.persistence.*


@Entity
data class Client(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
    @OneToMany(mappedBy = "client")
    val accounts: List<Account> = listOf()
)

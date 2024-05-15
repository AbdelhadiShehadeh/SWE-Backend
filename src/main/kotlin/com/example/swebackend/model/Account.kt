package com.example.swebackend.model

import jakarta.persistence.*


@Entity
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var balance: Double = 0.0,
    @ManyToOne @JoinColumn(name = "client_id")
    val client: Client
)

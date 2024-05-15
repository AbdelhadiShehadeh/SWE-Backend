package com.example.swebackend.repository

import com.example.swebackend.model.Client
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface ClientRepository : CrudRepository<Client, Long> {
    fun findByEmail(email: String): Optional<Client>
}

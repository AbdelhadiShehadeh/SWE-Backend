package com.example.swebackend.repository

import com.example.swebackend.model.Account
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, Long>

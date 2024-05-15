package com.example.swebackend.service

import com.example.swebackend.model.Account
import com.example.swebackend.model.Client
import com.example.swebackend.repository.AccountRepository
import com.example.swebackend.repository.ClientRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BankingService(
    private val clientRepository: ClientRepository,
    private val accountRepository: AccountRepository
) {

    fun registerClient(firstName: String, email: String, lastName: String, password: String, amount: Double): Client {
        val client =  clientRepository.save(Client(firstName = firstName, lastName = lastName, password = password, email = email))
        val account = Account(balance = amount, client = client)
        accountRepository.save(account)
        return client
    }

    fun isUserRegistered(email: String): Boolean {
        return clientRepository.findByEmail(email).isPresent
    }

    fun getAccountDetails(accountId: Long): Account {
        return accountRepository.findById(accountId).orElseThrow { RuntimeException("Account not found") }
    }

    @Transactional
    fun transferMoney(fromAccountId: Long, toAccountId: Long, amount: Double) {
        var fromAccount = accountRepository.findById(fromAccountId).orElseThrow { RuntimeException("Source account not found") }
        var toAccount = accountRepository.findById(toAccountId).orElseThrow { RuntimeException("Destination account not found") }

        if (fromAccount.balance < amount) {
            throw RuntimeException("Insufficient balance")
        }

        fromAccount.balance -= amount
        toAccount.balance += amount

        accountRepository.save(fromAccount)
        accountRepository.save(toAccount)
    }
}

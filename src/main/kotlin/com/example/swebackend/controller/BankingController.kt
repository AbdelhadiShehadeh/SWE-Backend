
    package com.example.swebackend.controller

    import com.example.swebackend.model.Account
    import com.example.swebackend.model.Client
    import com.example.swebackend.service.BankingService
    import org.springframework.http.ResponseEntity
    import org.springframework.web.bind.annotation.*

    @RestController
    class BankingController(private val bankingService: BankingService) {

        @PostMapping("/register")
        fun registerClient(@RequestBody client: ClientRegistrationRequest): ResponseEntity<Client> {
            val user = bankingService.registerClient(client.firstName,client.email,client.lastName,client.password, client.amount)
            return ResponseEntity.ok(user)
        }

        @GetMapping("/isRegistered")
        fun isUserRegistered(@RequestParam email: String, @RequestParam password: String): ResponseEntity<Long?> {
            val isRegistered = bankingService.isUserRegistered(email, password )
            return ResponseEntity.ok(isRegistered)
        }

        @GetMapping("/account/{id}")
        fun getAccountDetails(@PathVariable id: Long): ResponseEntity<AccountResponse> {
            val account = bankingService.getAccountDetails(id)
            val accountResponse = AccountResponse(id = account.id!!, balance = account.balance)
            return ResponseEntity.ok(accountResponse)
        }

        @PostMapping("/transfer")
        fun transferMoney(@RequestParam fromAccountId: Long, @RequestParam toAccountId: Long, @RequestParam amount: Double): ResponseEntity<String> {
            bankingService.transferMoney(fromAccountId, toAccountId, amount)
            return ResponseEntity.ok("Transfer successful")
        }
    }
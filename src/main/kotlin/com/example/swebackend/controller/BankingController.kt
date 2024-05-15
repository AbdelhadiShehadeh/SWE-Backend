
    package com.example.swebackend.controller

    import com.example.swebackend.model.Account
    import com.example.swebackend.model.Client
    import com.example.swebackend.service.BankingService
    import org.springframework.http.ResponseEntity
    import org.springframework.web.bind.annotation.*

    @RestController
    class BankingController(private val bankingService: BankingService) {

        @PostMapping("/register")
        fun registerClient(@RequestParam firstName :String, @RequestParam email :String, @RequestParam lastName :String, @RequestParam password :String, @RequestParam amount: Double): ResponseEntity<Client> {
            val user = bankingService.registerClient(firstName, lastName, password, email, amount)
            return ResponseEntity.ok(user)
        }

        @GetMapping("/isRegistered")
        fun isUserRegistered(@RequestParam email: String, @RequestParam password: String): ResponseEntity<Boolean> {
            val isRegistered = bankingService.isUserRegistered(email, password )
            return ResponseEntity.ok(isRegistered)
        }

        @GetMapping("/account/{id}")
        fun getAccountDetails(@PathVariable id: Long): ResponseEntity<Account> {
            val account = bankingService.getAccountDetails(id)
            return ResponseEntity.ok(account)
        }

        @PostMapping("/transfer")
        fun transferMoney(@RequestParam fromAccountId: Long, @RequestParam toAccountId: Long, @RequestParam amount: Double): ResponseEntity<String> {
            bankingService.transferMoney(fromAccountId, toAccountId, amount)
            return ResponseEntity.ok("Transfer successful")
        }
    }

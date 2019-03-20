package com.jeonguk.web.controller

import com.jeonguk.web.entity.redis.Transaction
import com.jeonguk.web.exception.ResourceNotFoundException
import com.jeonguk.web.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * user redis
 */
@RestController
@RequestMapping("/api")
class TransactionController {

    @Autowired
    lateinit var repository: TransactionRepository

    @PostMapping("/transactions")
    fun add(@RequestBody transaction: Transaction): Transaction {
        return repository.save(transaction)
    }

    @GetMapping("/transactions/{id}")
    fun findById(@PathVariable("id") id: Long): Transaction {
        val optTransaction = repository.findById(id)
        return if (optTransaction.isPresent)
            optTransaction.get()
        else
            throw ResourceNotFoundException("Transaction Not Found")
    }

    @GetMapping("/transactions/from/{accountId}")
    fun findByFromAccountId(@PathVariable("accountId") accountId: Long): List<Transaction> {
        return repository.findByFromAccountId(accountId)
    }

    @GetMapping("/transactions/to/{accountId}")
    fun findByToAccountId(@PathVariable("accountId") accountId: Long): List<Transaction> {
        return repository.findByToAccountId(accountId)
    }

}
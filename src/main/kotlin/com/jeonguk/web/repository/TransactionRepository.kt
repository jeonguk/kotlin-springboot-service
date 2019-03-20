package com.jeonguk.web.repository

import com.jeonguk.web.entity.redis.Transaction
import org.springframework.data.repository.CrudRepository

interface TransactionRepository: CrudRepository<Transaction, Long> {
    fun findByFromAccountId(fromAccountId: Long): List<Transaction>
    fun findByToAccountId(toAccountId: Long): List<Transaction>
}
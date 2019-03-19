package com.jeonguk.web.repository.redis

import com.jeonguk.web.domain.h2.Transaction
import org.springframework.data.repository.CrudRepository

interface TransactionRepository: CrudRepository<Transaction, Long> {
    fun findByFromAccountId(fromAccountId: Long): List<Transaction>
    fun findByToAccountId(toAccountId: Long): List<Transaction>
}
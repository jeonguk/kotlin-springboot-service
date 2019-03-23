package com.jeonguk.web.repository.redis

import com.jeonguk.web.entity.redis.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: JpaRepository<Transaction, Long> {
    fun findByFromAccountId(fromAccountId: Long): List<Transaction>
    fun findByToAccountId(toAccountId: Long): List<Transaction>
}
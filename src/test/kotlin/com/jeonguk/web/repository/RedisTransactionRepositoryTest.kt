package com.jeonguk.web.repository

import com.jeonguk.web.entity.redis.Transaction
import com.jeonguk.web.repository.redis.TransactionRepository
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@SpringBootTest
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedisTransactionRepositoryTest {

    @Autowired
    lateinit var repository: TransactionRepository

    @Test
    fun `test add`() {
        var transaction = Transaction(1L, 1000, Date(), 20L, 40L)
        transaction = repository.save(transaction)
        assertNotNull(transaction)
    }

    @Test
    fun `test findByFromAccountId`() {
        val transactions = repository.findByFromAccountId(20L)
        assertTrue(transactions.size == 1)
    }

    @Test
    fun `test findByToAccountId`() {
        val transactions = repository.findByToAccountId(40L)
        assertTrue(transactions.size == 1)
    }

}
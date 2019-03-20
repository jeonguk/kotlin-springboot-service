package com.jeonguk.web.repository

import com.jeonguk.web.entity.redis.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: CrudRepository<Customer, Long> {
    fun findByExternalId(externalId: String): Customer
    fun findByAccountsId(id: Long): List<Customer>
}

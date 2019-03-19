package com.jeonguk.web.repository.redis

import com.jeonguk.web.domain.h2.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: CrudRepository<Customer, Long> {
    fun findByExternalId(externalId: String): Customer
    fun findByAccountsId(id: Long): List<Customer>
}

package com.jeonguk.web.repository.redis

import com.jeonguk.web.entity.redis.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {
    fun findByExternalId(externalId: String): Customer
    fun findByAccountsId(id: Long): List<Customer>
}

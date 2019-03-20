package com.jeonguk.web.repository

import com.jeonguk.web.entity.redis.Account
import com.jeonguk.web.entity.redis.Customer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RedisCustomerRepositoryTest {

    @Autowired
    lateinit var repository: CustomerRepository

    @Test
    fun `test add`() {
        val customer = Customer(1L, "80010121098", "John Smith")
        val arrAccounts = arrayListOf<Account>()
        arrAccounts.add(Account(1L, "1234567890", 2000))
        arrAccounts.add(Account(2L, "1234567891", 4000))
        arrAccounts.add(Account(3L, "1234567892", 6000))
        customer.accounts = arrAccounts

        val saveCustomer = repository.save(customer)
        assertNotNull(saveCustomer)
    }

    @Test
    fun `test findByAccounts`() {
        val customers: List<Customer> = repository.findByAccountsId(3L)
        assertEquals(1, customers.size)
        val customer = customers[0]
        assertNotNull(customer)
       assertEquals(1L, customer.id)
    }

    @Test
    fun `test findByExternal`() {
        val customer = repository.findByExternalId("80010121098")
        assertNotNull(customer)
        assertEquals(1L, customer.id)
    }

}
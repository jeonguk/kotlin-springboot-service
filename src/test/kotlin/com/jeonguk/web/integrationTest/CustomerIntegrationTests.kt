package com.jeonguk.web.integrationTest

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension
import com.jeonguk.web.entity.redis.Customer
import com.jeonguk.web.entity.redis.Account
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerIntegrationTests {

    @Autowired
    lateinit var template: TestRestTemplate

    @BeforeEach
    fun init() {

    }

    @Test
    fun `test add and find`() {
        val customer = Customer(1L, "123456", "John Smith")
        val arrAccounts = arrayListOf<Account>()
        arrAccounts.add(Account(1L, "1234567890", 2000))
        arrAccounts.add(Account(2L, "1234567891", 4000))
        customer.accounts = arrAccounts

        val postCustomer = template.postForObject("/api/customers", customer, Customer::class.java)
        assertNotNull(postCustomer)
        val getCustomer = template.getForObject("/api/customers/{id}", Customer::class.java, 1)
        assertNotNull(getCustomer)
        assertEquals("123456", getCustomer.externalId)
        assertEquals(2, getCustomer.accounts.size)
    }

}
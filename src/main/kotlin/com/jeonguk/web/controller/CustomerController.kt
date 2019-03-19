package com.jeonguk.web.controller

import com.jeonguk.web.repository.redis.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.jeonguk.web.domain.h2.Customer
import com.jeonguk.web.exception.ResourceNotFoundException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping

/**
 * use redis
 */
@RestController
@RequestMapping("/api")
class CustomerController {

    @Autowired
    lateinit var repository: CustomerRepository

    @PostMapping("/customers")
    fun add(@RequestBody customer: Customer): Customer {
        return repository.save(customer)
    }

    @GetMapping("/customers/{id}")
    fun findById(@PathVariable("id") id: Long): Customer {
        val optCustomer = repository.findById(id)
        return if (optCustomer.isPresent)
            optCustomer.get()
        else
            throw ResourceNotFoundException("Customer Not Found")
    }

}
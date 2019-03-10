package com.jeonguk.web

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Assert get users, content and status code`() {
        println(">> TODO")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}
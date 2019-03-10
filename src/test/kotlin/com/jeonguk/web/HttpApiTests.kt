package com.jeonguk.web

import com.jeonguk.web.config.WebConfig
import com.jeonguk.web.model.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(SpringExtension::class)
@WebAppConfiguration
@AutoConfigureMockMvc
@ContextConfiguration(classes = [WebConfig::class])
@SpringBootTest(classes = [Application::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpApiTests(@Autowired val wac: WebApplicationContext, @Autowired var mockMvc: MockMvc) {

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build()
    }

    @Test
    fun `List users`() {
        val user = User(1, "jeonguk")

        mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.[0].user_id").value(user.userId))
                .andExpect(jsonPath("\$.[0].user_name").value(user.userName))
    }


}
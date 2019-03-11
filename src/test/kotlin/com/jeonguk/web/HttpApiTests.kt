package com.jeonguk.web

import com.jeonguk.web.model.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpApiTests(@Autowired var mockMvc: MockMvc) {


    @Test
    fun `List users`() {
        val user = User(1, "jeonguk")

        mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.[0].user_id").value(user.userId))
                .andExpect(jsonPath("\$.[0].user_name").value(user.userName))
                .andDo(MockMvcResultHandlers.print())
    }


}
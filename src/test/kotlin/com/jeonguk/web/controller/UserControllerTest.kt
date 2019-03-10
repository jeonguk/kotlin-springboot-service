package com.jeonguk.web.controller

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jeonguk.web.model.User
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest(@Autowired var context: WebApplicationContext, @Autowired var mockMvc: MockMvc) {

    @BeforeEach
    fun setUp(restDocumentation: RestDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply<DefaultMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build()
    }

    @Test
	fun `Gson test`() {

        val user = User(1, "jeonguk")

		val expected = "{\n" +
				"\"user_id\": 1,\n" +
				"\"user_name\": \"jeonguk\"" +
				"}"

		val gson = GsonBuilder()
				//.serializeNulls()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.setPrettyPrinting()
				.create()

		val data = gson.toJson(user)

		JSONAssert.assertEquals(expected, data, false)
	}

    @Test
    fun `Assert get api`() {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcRestDocumentation.document("user"))
    }

    @Test
    fun `List getUsers`() {
        val user = User(1, "jeonguk")

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].user_id").value(user.userId))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].user_name").value(user.userName))
                .andDo(MockMvcResultHandlers.print())
                .andDo(MockMvcRestDocumentation.document("index"))
    }

}
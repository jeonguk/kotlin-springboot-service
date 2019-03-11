package com.jeonguk.web.controller

import com.google.gson.Gson
import com.jeonguk.web.model.User
import com.jeonguk.web.util.ApiDocumentUtils.Companion.documentRequest
import com.jeonguk.web.util.ApiDocumentUtils.Companion.documentResponse
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
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SampleControllerTest(
        @Autowired var context: WebApplicationContext,
        @Autowired var mockMvc: MockMvc,
        @Autowired var gson: Gson) {

    @BeforeEach
    fun setUp(restDocumentation: RestDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply<DefaultMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(restDocumentation).snippets().withEncoding("UTF-8"))
                .build()
    }

    @Test
    fun `Gson test`() {

        val user = User(1, "jeonguk")

        val expected = "{\n" +
                "\"user_id\": 1,\n" +
                "\"user_name\": \"jeonguk\"" +
                "}"

        val data = gson.toJson(user)

        JSONAssert.assertEquals(expected, data, false)
    }

    @Test
    fun `List getUsers`() {
        val user = User(1, "jeonguk")

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.[0].user_id").value(user.userId))
                .andExpect(jsonPath("\$.[0].user_name").value(user.userName))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("sample-getusers"))

    }

    @Test
    fun `Post user`() {
        // given
        val user = User(4, "jeonguk")

        // when
        val result: ResultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))

        // then
        result.andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.user_id").value(user.userId))
                .andExpect(jsonPath("\$.user_name").value(user.userName))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("sample-postuser",
                        documentRequest,
                        documentResponse,
                        responseFields(
                                fieldWithPath("user_id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("user_name").type(JsonFieldType.STRING).description("이름")
                        )
                ))


    }

    @Test
    fun `Put user`() {
        // given
        val user = User(1, "jeonguk")

        // when
        val result: ResultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.put("/api/user/{userId}", 1L)
                        .content(gson.toJson(user))
                        .contentType(MediaType.APPLICATION_JSON))

        // then
        result.andExpect(status().isOk)
                .andDo(document("sample-putuser",
                        documentRequest,
                        documentResponse,
                        pathParameters(
                                parameterWithName("userId").description("아이디")
                        ),
                        requestFields(
                                fieldWithPath("user_id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("user_name").type(JsonFieldType.STRING).description("이름")
                        ),
                        responseFields(
                                fieldWithPath("user_id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("user_name").type(JsonFieldType.STRING).description("이름")
                        )
                ))

    }

}
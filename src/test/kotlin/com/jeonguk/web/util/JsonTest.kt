package com.jeonguk.web.util

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeonguk.web.domain.h2.Response
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.slf4j.LoggerFactory

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JsonTest {

    private val logger = LoggerFactory.getLogger(JsonTest::class.java)

    private val gson: Gson =  GsonBuilder()
            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            //.setPrettyPrinting()
            .create()

    @BeforeEach
    fun init() {
    }

    @Test
    fun `json to kotlin data class mapping test`() {
        val jsonString = "{" +
            "\"first_name\":\"John\"," +
            "\"last_name\":\"Smith\","+
            "\"gender\":\"man\"," +
            "\"age\":32," +
            "\"grid\":[" +
                    "{" +
                        "\"street_address\":\"21 2nd Street 1\"," +
                        "\"city\":\"New York 1\"," +
                        "\"state\":\"NY 1\"," +
                        "\"postal_code\":\"10021 1\""+
                    "}," +
                    "{" +
                        "\"street_address\":\"21 2nd Street 2\"," +
                        "\"city\":\"New York 2\"," +
                        "\"state\":\"NY 2\"," +
                        "\"postal_code\":\"10021 2\""+
                    "}" +
                "]" +
            "}"
        logger.info("jsonString {}", jsonString)
        val response =  gson.fromJson(jsonString, Response::class.java)

        logger.info("VALUE 1 {}", response)

        val convertJson = gson.toJson(response)
        logger.info("VALUE 2 {}", convertJson)
        assertEquals(convertJson, jsonString)
    }
}
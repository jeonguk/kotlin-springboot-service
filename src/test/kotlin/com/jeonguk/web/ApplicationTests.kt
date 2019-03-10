package com.jeonguk.web

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class ApplicationTests {

	@Test
	fun contextLoads() {
	}

//	lateinit var user: User
//
//	@Before
//	fun setUp() {
//		user = User(1, "jeonguk")
//	}
//
//	@Test
//	fun simpleGsonTest() {
//
//		val expected = "{\n" +
//				"\"user_id\": 1,\n" +
//				"\"user_name\": \"jeonguk\"" +
//				"}"
//
//		val gson = GsonBuilder()
//				//.serializeNulls()
//				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//				.setPrettyPrinting()
//				.create()
//
//		val data = gson.toJson(user)
//
//		JSONAssert.assertEquals(expected, data, false)
//	}

//	@Test
//	@Throws(JSONException::class)
//	fun errorGsonTest() {
//
//		val expected = "{\n" +
//				"\"user_id\": 11,\n" +
//				"\"user_name\": \"jeonguk\"" +
//				"}"
//
//		val gson = GsonBuilder()
//				//.serializeNulls()
//				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//				.setPrettyPrinting()
//				.create()
//
//		val data = gson.toJson(user)
//
//		JSONAssert.assertEquals(expected, data, false)
//	}
}

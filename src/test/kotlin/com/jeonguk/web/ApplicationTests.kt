package com.jeonguk.web

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jeonguk.web.model.User
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import sun.plugin2.util.PojoUtil.toJson
import com.google.gson.Gson
import org.json.JSONException


@RunWith(SpringRunner::class)
@SpringBootTest
class ApplicationTests {

	lateinit var user: User

	@Before
	fun setUp() {
		user = User(1, "jeonguk")
	}

	@Test
	fun simpleGsonTest() {

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
	@Throws(JSONException::class)
	fun errorGsonTest() {

		val expected = "{\n" +
				"\"user_id\": 11,\n" +
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
}

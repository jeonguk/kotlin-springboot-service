package com.jeonguk.web

import com.jeonguk.web.model.User
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class KotlinSpringbootServiceApplication {

	companion object {
		val initialUsers = arrayOf(
				User(1, "11111"),
				User(2, "222222"),
				User(3, "3333333")
		)
	}

	@Bean
	fun users() = ConcurrentHashMap<Int, User>(initialUsers.associateBy(User::userId))
}

fun main(args: Array<String>) {
	runApplication<KotlinSpringbootServiceApplication>(*args)
}

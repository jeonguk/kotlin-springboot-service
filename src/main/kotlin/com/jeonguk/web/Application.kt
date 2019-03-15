package com.jeonguk.web

import com.jeonguk.web.model.User
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication(
		exclude = [
			DataSourceAutoConfiguration::class,
			HibernateJpaAutoConfiguration::class,
			DataSourceTransactionManagerAutoConfiguration::class],
		scanBasePackages = ["com.jeonguk.web"]
)
@EnableTransactionManagement
class Application {

	companion object {
		val initialUsers = arrayOf(
				User(1, "jeonguk"),
				User(2, "222222"),
				User(3, "3333333")
		)
	}

	@Bean
	fun users() = ConcurrentHashMap<Int, User>(initialUsers.associateBy(User::userId))
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

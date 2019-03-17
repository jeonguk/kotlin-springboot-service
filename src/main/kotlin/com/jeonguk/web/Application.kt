package com.jeonguk.web

import com.jeonguk.web.entity.Post
import com.jeonguk.web.model.User
import com.jeonguk.web.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication(
		exclude = [
			DataSourceAutoConfiguration::class,
			HibernateJpaAutoConfiguration::class,
			DataSourceTransactionManagerAutoConfiguration::class],
		scanBasePackages = ["com.jeonguk.web"]
)
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

	@Bean
	fun applicationRunner(): ApplicationRunner {
		return object : ApplicationRunner {

			@Autowired
			lateinit var postRepository: PostRepository

			@Throws(Exception::class)
			override fun run(args: ApplicationArguments) {
				postRepository.deleteAll()

				for (i in 1..10) {
					val post = Post(0,
							postTitle = "post title $i",
							postContent = "post content $i",
							amount = BigDecimal("1234567800"),
							createdAt = LocalDateTime.now()
					)
					postRepository.save(post)
				}

			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

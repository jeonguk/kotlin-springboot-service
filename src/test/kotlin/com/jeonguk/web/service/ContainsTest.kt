package com.jeonguk.web.service

import com.jeonguk.web.controller.PostController
import com.jeonguk.web.repository.redis.PersonRepository
import com.jeonguk.web.test.Person
import com.jeonguk.web.test.PersonDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.stream.Collectors

data class Person(
        var userId: String,
        var userName: String,
        var userAdress: String
)

data class PersonDto(
        var userId: String,
        val userName: String
)


@SpringBootTest
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContainsTest {

    private val logger = LoggerFactory.getLogger(PostController::class.java)

    @Autowired
    lateinit var repository: PersonRepository

    @BeforeAll
    fun init() {
        val person  = com.jeonguk.web.entity.redis.Person("id4", "name3")
        repository.save(person)
    }

    @Test
    fun `External data Rdb Data redis data contains test`() {

        // External array data
        val targetArray = arrayListOf(
                Person("id1", "name1", "address1"),
                Person("id2", "name2", "address2"),
                Person("id3", "name3", "address3")
        )

        // RDB Data
        val rdbData = Person("id3", "name2", "address2")

        // Cache Data
        val cacheData = repository.findById(rdbData.userId)

        val checkResult: Boolean

        // 최신 데이터 한개
        //val firstData = targetArray.stream().findFirst()

        checkResult = if (cacheData.isPresent) { // if cached
            targetArray.stream()
                    .map { p -> PersonDto(p.userId, p.userName) }
                    .collect(Collectors.toList())
                    .contains(PersonDto(cacheData.get().usreId, cacheData.get().userName))
        } else {
            targetArray.stream()
                    .map { p -> PersonDto(p.userId, p.userName) }
                    .collect(Collectors.toList())
                    .contains(PersonDto(rdbData.userId, rdbData.userName))

            // Cache data 를 RDB 값으로 업데이트
        }

        logger.info("cache userId {}", checkResult)
        assertEquals(checkResult, true)

    }

}
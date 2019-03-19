package com.jeonguk.web.controller

import com.jeonguk.web.domain.User
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class SampleController {

    private val logger = LoggerFactory.getLogger(SampleController::class.java)

    @Autowired
    lateinit var users: ConcurrentHashMap<Int, User>

    @RequestMapping
    fun index(): String {
        return "Hello, World"
    }

    @GetMapping("/user/{userId}")
    fun getUser(@PathVariable userId: Int) = users[userId]

    @GetMapping("/users")
    fun getUsers() = users.map(Map.Entry<Int, User>::value).toList()

    @PostMapping("/user")
    fun postUser(@Valid @RequestBody user: User): User {
        users[user.userId] = user
        return user
    }

    @DeleteMapping("/user/{userId}")
    fun deleteUser(@PathVariable userId: Int) = users.remove(userId)

    @PutMapping("/user/{userId}")
    fun putUser(@PathVariable userId: Int, @Valid @RequestBody user: User): User {
        users.remove(userId)
        users[user.userId] = user
        return user
    }

}
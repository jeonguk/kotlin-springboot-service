package com.jeonguk.web.entity.redis

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("person")
data class Person(
    @Id
    var usreId: String,
    @Indexed
    var userName: String
)
package com.jeonguk.web.entity.redis

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import javax.persistence.Id

@RedisHash("customer")
data class Customer(
        @Id
        var id: Long,
        @Indexed
        var externalId: String,
        var name: String,
        var accounts: List<Account> = arrayListOf()
)
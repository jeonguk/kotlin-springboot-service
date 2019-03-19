package com.jeonguk.web.domain.h2

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.util.*
import javax.persistence.Id

@RedisHash("transaction")
class Transaction(
        @Id
        var id: Long,
        var amount: Int,
        var data: Date,
        @Indexed
        var fromAccountId: Long,
        @Indexed
        var toAccountId: Long
)
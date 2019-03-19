package com.jeonguk.web.domain.h2

import org.springframework.data.redis.core.index.Indexed

data class Account(
        @Indexed
        var id: Long,
        var number: String,
        var balance: Int
)
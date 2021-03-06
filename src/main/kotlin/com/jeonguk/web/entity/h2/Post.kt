package com.jeonguk.web.entity.h2

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val postTitle: String,
        val postContent: String,
        val amount: BigDecimal,
        val createdAt: LocalDateTime
)
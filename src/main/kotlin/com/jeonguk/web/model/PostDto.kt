package com.jeonguk.web.model

import java.time.LocalDateTime

data class PostDto(
        val id: Long,
        val postTitle: String,
        val postContent: String,
        val amount: String,
        val createdAt: LocalDateTime
)
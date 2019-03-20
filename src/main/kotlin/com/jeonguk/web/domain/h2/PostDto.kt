package com.jeonguk.web.domain.h2

import org.apache.commons.lang.StringUtils
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class RequestPost(
    @get:NotBlank
    var postTitle: String,
    @get:NotBlank
    var postContent: String,
    var amount: String = "0",
    var createdAt: LocalDateTime = LocalDateTime.now()
)

data class RequestPostDto(
    @get:NotBlank
    var postTitle: String = StringUtils.EMPTY,
    var postContent: String = "default"
)

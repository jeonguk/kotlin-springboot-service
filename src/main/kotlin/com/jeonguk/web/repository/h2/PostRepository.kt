package com.jeonguk.web.repository.h2

import com.jeonguk.web.entity.h2.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Long>
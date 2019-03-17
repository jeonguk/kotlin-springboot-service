package com.jeonguk.web.service

import com.jeonguk.web.entity.Post
import com.jeonguk.web.model.PostDto
import com.jeonguk.web.repository.PostRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.lang.String.valueOf
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class PostService {

    private val logger = LoggerFactory.getLogger(PostService::class.java)

    @Autowired
    lateinit var postRepository: PostRepository

    fun getPost(postId: Long): ResponseEntity<PostDto> {
        return postRepository.findById(postId).map { post ->
             val postDto = PostDto(post.id, post.postTitle, post.postContent, valueOf(post.amount), post.createdAt)
            ResponseEntity.ok().body(postDto)
        }.orElse(ResponseEntity.notFound().build())
    }

    fun getPosts(): ResponseEntity<MutableList<Post>> {
        val postList: MutableList<Post> =  postRepository.findAll()

        val check = postList.contains(Post(1L, "post title 1", "post content 1", BigDecimal("123456789"), LocalDateTime.parse("2019-03-17T18:03:13.637Z", DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'"))))

        logger.info("CHECK {}", check)

        return ResponseEntity.ok().body(postList)
    }

}
package com.jeonguk.web.controller

import com.jeonguk.web.entity.h2.Post
import com.jeonguk.web.domain.h2.RequestPost
import com.jeonguk.web.domain.h2.RequestPostDto
import com.jeonguk.web.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PostController {

    private val logger = LoggerFactory.getLogger(PostController::class.java)

    @Autowired
    lateinit var postService: PostService

    @GetMapping("/post/{postId}")
    fun getPost(@PathVariable("postId") postId: Long): ResponseEntity<RequestPost> = postService.getPost(postId)

    @GetMapping("/posts")
    fun getPosts(): ResponseEntity<MutableList<Post>> = postService.getPosts()

    @PostMapping("posts")
    fun savePosts(@Valid @RequestBody request: RequestPostDto): RequestPostDto {
        logger.info("post title $request.postTitle")
        logger.info("post content $request.postContent")
        return request
    }

}
package com.jeonguk.web.controller

import com.jeonguk.web.entity.Post
import com.jeonguk.web.model.PostDto
import com.jeonguk.web.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PostController {

    @Autowired
    lateinit var postService: PostService

    @GetMapping("/post/{postId}")
    fun getPost(@PathVariable("postId") postId: Long): ResponseEntity<PostDto> = postService.getPost(postId)

    @GetMapping("/posts")
    fun getPosts(): ResponseEntity<MutableList<Post>> = postService.getPosts()
}
package com.jeonguk.web.service

import com.jeonguk.web.model.PostDto
import com.jeonguk.web.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.lang.String.valueOf

@Service
class PostService {

    @Autowired
    lateinit var postRepository: PostRepository

    fun getPost(postId: Long): ResponseEntity<PostDto> {
        return postRepository.findById(postId).map { post ->
             val postDto = PostDto(post.postId, post.postTitle, post.postContent, valueOf(post.amount), post.createdAt)
            ResponseEntity.ok().body(postDto)
        }.orElse(ResponseEntity.notFound().build())
    }

}
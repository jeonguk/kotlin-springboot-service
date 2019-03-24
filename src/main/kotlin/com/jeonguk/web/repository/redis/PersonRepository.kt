package com.jeonguk.web.repository.redis

import com.jeonguk.web.entity.redis.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<Person, String>
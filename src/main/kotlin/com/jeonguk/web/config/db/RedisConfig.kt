package com.jeonguk.web.config.db

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

@Configuration
@EnableRedisRepositories
class RedisConfig {

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val template = RedisTemplate<ByteArray, ByteArray>()
        template.setConnectionFactory(redisConnectionFactory())
        return template
    }

}
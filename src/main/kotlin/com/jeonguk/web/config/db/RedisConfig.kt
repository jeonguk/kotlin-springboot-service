package com.jeonguk.web.config.db

import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableRedisRepositories(basePackages = ["com.jeonguk.web.repository.redis"])
class RedisConfig
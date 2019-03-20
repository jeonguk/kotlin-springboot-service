package com.jeonguk.web.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@ComponentScan("com.jeonguk.web")
@EnableAspectJAutoProxy
class AppConfig
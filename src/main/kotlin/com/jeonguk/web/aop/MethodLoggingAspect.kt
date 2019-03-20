package com.jeonguk.web.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import java.lang.Exception

@Aspect
@Component
class MethodLoggingAspect {

    @Before("execution(* *..*Service.*(..))")
    fun startLog(jp: JoinPoint) {
        println("method start : ${jp.signature}")
    }

    @Around("execution(* *..*Service.*(..))")
    fun log(jp: ProceedingJoinPoint): Any {
        println("Around method start : ${jp.signature}")
        try {
            // target method start
            val result: Any = jp.proceed()
            println("Around method end : ${jp.signature} result=$result")
            return result
        } catch (e: Exception) {
            println(" $jp.signature")
            e.printStackTrace()
            throw e
        }
    }

    @AfterReturning("execution(* *..*Service.*(..))")
    fun endLog(jp: JoinPoint) {
        println("method end : ${jp.signature}")
    }

}
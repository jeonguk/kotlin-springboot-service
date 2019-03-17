package com.jeonguk.web.config

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeonguk.web.config.adapter.BigDecimalTypeAdapter
import com.jeonguk.web.config.adapter.LocalDateTimeTypeAdapter
import com.jeonguk.web.config.adapter.LocalDateTypeAdapter
import com.jeonguk.web.config.adapter.LocalTimeTypeAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.GsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import java.math.BigDecimal
import java.nio.charset.StandardCharsets
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Configuration
class WebConfig : WebMvcConfigurationSupport() {

    val resourceLocation = arrayOf("classpath:/static/")

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(*resourceLocation)
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(gsonHttpMessageConverter())
        converters.add(StringHttpMessageConverter(StandardCharsets.UTF_8))
        converters.add(FormHttpMessageConverter())
        super.configureMessageConverters(converters)
    }

    @Bean
    fun gsonHttpMessageConverter(): GsonHttpMessageConverter {
        val gsonBuilder = GsonBuilder()
                .registerTypeAdapter(LocalDateTime::class.java, localDateTimeTypeAdapter())
                .registerTypeAdapter(LocalDate::class.java, localDateTypeAdapter())
                .registerTypeAdapter(LocalTime::class.java, localTimeTypeAdapter())
                .registerTypeAdapter(BigDecimal::class.java, bigDecimalTypeAdapter())
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
        val converter = GsonHttpMessageConverter()
        converter.gson = gsonBuilder.create()
        return converter
    }

    @Bean
    fun gson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(LocalDateTime::class.java, localDateTimeTypeAdapter())
                .registerTypeAdapter(LocalDate::class.java, localDateTypeAdapter())
                .registerTypeAdapter(LocalTime::class.java, localTimeTypeAdapter())
                .registerTypeAdapter(BigDecimal::class.java, bigDecimalTypeAdapter())
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create()
    }

    @Bean
    fun localDateTimeTypeAdapter(): LocalDateTimeTypeAdapter {
        return LocalDateTimeTypeAdapter()
    }

    @Bean
    fun localDateTypeAdapter(): LocalDateTypeAdapter {
        return LocalDateTypeAdapter()
    }

    @Bean
    fun localTimeTypeAdapter(): LocalTimeTypeAdapter {
        return LocalTimeTypeAdapter()
    }

    @Bean
    fun bigDecimalTypeAdapter(): BigDecimalTypeAdapter {
        return BigDecimalTypeAdapter()
    }

}
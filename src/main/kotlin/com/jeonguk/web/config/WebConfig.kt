package com.jeonguk.web.config

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.GsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import java.nio.charset.StandardCharsets

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
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create()
    }


}
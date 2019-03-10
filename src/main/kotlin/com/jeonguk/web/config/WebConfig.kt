package com.jeonguk.web.config

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.GsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class WebConfig : WebMvcConfigurationSupport() {

    val RESOURCE_LOCATIONS = arrayOf("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/")

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(gsonHttpMessageConverter())
        super.configureMessageConverters(converters)
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(*RESOURCE_LOCATIONS)
    }

    @Bean
    fun gsonHttpMessageConverter(): GsonHttpMessageConverter {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.setPrettyPrinting()
        val converter = GsonHttpMessageConverter()
        converter.gson = gsonBuilder.create()
        return converter
    }

}

//class CustomGsonHttpMessageConverter : GsonHttpMessageConverter() {
//    init {
//        super.setGson(GsonBuilder()
//                //.serializeNulls()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                .setPrettyPrinting()
//                .create())
//    }
//}
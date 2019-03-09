package com.jeonguk.web.config

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.GsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class WebConfig : WebMvcConfigurationSupport() {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(CustomGsonHttpMessageConverter())
        super.configureMessageConverters(converters)
    }

}

class CustomGsonHttpMessageConverter : GsonHttpMessageConverter() {
    init {
        super.setGson(GsonBuilder()
                //.serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create())
    }
}
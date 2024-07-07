package com.ssu.commerce.gateway.config

import java.util.stream.Collectors
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter


@Configuration
class FeignFluxConfig {
    @Bean
    @ConditionalOnMissingBean
    fun messageConverters(converters: ObjectProvider<HttpMessageConverter<*>?>): HttpMessageConverters {
        return HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()))
    }
}
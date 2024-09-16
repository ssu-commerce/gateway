package com.ssu.commerce.gateway.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("cors")
class CorsConfig {
    lateinit var allowedOrigin: List<String>

    @Bean
    fun corsWebFilter(): CorsWebFilter {
        val corsConfig = CorsConfiguration()
            .apply {
                allowCredentials = true
                addAllowedHeader("*")
                addAllowedMethod("*")
                allowedOrigin.forEach { addAllowedHeader(it) }
            }
        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfig)
        return CorsWebFilter(corsConfigurationSource)
    }
}

package com.ssu.commerce.gateway

import com.ssu.commerce.core.EnableSsuCommerceCore
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableSsuCommerceCore
class GatewayApplication

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}

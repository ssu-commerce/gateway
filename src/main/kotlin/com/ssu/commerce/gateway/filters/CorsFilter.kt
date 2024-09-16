package com.ssu.commerce.gateway.filters

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CorsFilter : AbstractGatewayFilterFactory<Any>() {
    override fun apply(config: Any?): GatewayFilter =
        GatewayFilter { exchange, chain ->
            val request = exchange.request

            request.mutate().headers { headers ->
                headers.remove("Origin")
            }
            chain.filter(exchange).then(Mono.fromRunnable {})
        }
}

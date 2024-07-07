package com.ssu.commerce.gateway.filters

import java.net.URI
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR
import org.springframework.core.Ordered
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class LoggingFilter : AbstractGatewayFilterFactory<Any>() {
    val log: Logger = LoggerFactory.getLogger(LoggingFilter::class.java)

    override fun apply(config: Any): GatewayFilter {
        val filter: GatewayFilter = OrderedGatewayFilter({ exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request: ServerHttpRequest = exchange.request
            val response: ServerHttpResponse = exchange.response
            chain.filter(exchange).then(
                Mono.fromRunnable {
                    val path: String =
                        exchange.getAttribute<LinkedHashSet<URI>>(GATEWAY_ORIGINAL_REQUEST_URL_ATTR)!!.toList()[0].path
                    log.info("${response.rawStatusCode} ${request.method} $path")
                }
            )
        }, Ordered.LOWEST_PRECEDENCE)

        return filter
    }
}

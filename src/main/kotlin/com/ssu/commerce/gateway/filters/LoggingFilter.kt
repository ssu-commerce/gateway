package com.ssu.commerce.gateway.filters

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.net.URI

@Component
class LoggingFilter : AbstractGatewayFilterFactory<Any>() {
    val log: Logger = LoggerFactory.getLogger(LoggingFilter::class.java)

    override fun apply(config: Any): GatewayFilter =
        GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            chain.filter(exchange).then(appendLog(exchange))
        }

    fun appendLog(exchange: ServerWebExchange) =
        Mono.fromRunnable<Void> {
            val path = exchange.getAttribute<LinkedHashSet<URI>>(GATEWAY_ORIGINAL_REQUEST_URL_ATTR)!!.toList()[0].path
            log.info("${exchange.response.rawStatusCode} ${exchange.request.method} $path")
        }
}

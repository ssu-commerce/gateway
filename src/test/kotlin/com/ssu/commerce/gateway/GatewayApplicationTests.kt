package com.ssu.commerce.gateway

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.profiles.active:test"])
class GatewayApplicationTests {

    @Test
    fun contextLoads() {
    }
}

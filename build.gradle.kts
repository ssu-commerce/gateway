import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    id("com.ssu.commerce.plugin.github-registry")
    id("com.ssu.commerce.plugin.docker-publish")
}

group = "com.ssu.commerce"
version = System.getenv("VERSION") ?: "NoVersion"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    api("com.ssu.commerce:ssu-commerce-config-client:2024.03.1")
    implementation("io.netty:netty-resolver-dns-native-macos:4.1.75.Final:osx-aarch_64")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

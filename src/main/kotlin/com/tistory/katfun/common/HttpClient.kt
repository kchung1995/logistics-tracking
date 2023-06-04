package com.tistory.katfun.common

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

val client = HttpClient(CIO) {
    install(Logging) {
        level = LogLevel.INFO
    }
    install(ContentNegotiation) {
        json()
    }
}

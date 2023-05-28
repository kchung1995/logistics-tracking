package com.tistory.katfun.plugins

import com.tistory.katfun.logistics.logisticsRouting
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        logisticsRouting()
    }
}

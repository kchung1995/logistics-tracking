package com.tistory.katfun

import com.tistory.katfun.plugins.configureRouting
import com.tistory.katfun.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureRouting()
}

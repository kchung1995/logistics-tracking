package com.tistory.katfun.logistics

import com.tistory.katfun.logistics.models.TrackerDeliveryResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

suspend fun trackerDeliveryClient(): TrackerDeliveryResponse {
    val client = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json()
        }
    }

    val response: TrackerDeliveryResponse = client.get("https://apis.tracker.delivery/carriers") {
        url {
            appendPathSegments("kr.cjlogistics", "tracks", "568833612760")
        }
    }.body()
    client.close()
    return response
}

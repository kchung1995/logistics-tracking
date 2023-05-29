package com.tistory.katfun.logistics

import com.tistory.katfun.logistics.models.TrackerDeliveryResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import io.ktor.serialization.kotlinx.json.json

suspend fun trackerDeliveryClient(carrier: String, invoiceNumber: String): TrackerDeliveryResponse {
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
            appendPathSegments(carrier, "tracks", invoiceNumber)
        }
    }.body()
    client.close()
    return response
}

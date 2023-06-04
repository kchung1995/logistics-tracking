package com.tistory.katfun.logistics

import com.tistory.katfun.common.client
import com.tistory.katfun.logistics.models.TrackerDeliveryResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.Url
import io.ktor.http.appendPathSegments

suspend fun trackerDeliveryClient(carrier: String, invoiceNumber: String): TrackerDeliveryResponse {
    val deliveryTrackerBasePath = Url("https://apis.tracker.delivery")
    val response: TrackerDeliveryResponse = client.get(deliveryTrackerBasePath) {
        url {
            appendPathSegments("carriers", carrier, "tracks", invoiceNumber)
        }
    }.body()
    client.close()
    return response
}

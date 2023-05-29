package com.tistory.katfun.logistics

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.logisticsRouting() {
    route("/api/v1/logistics") {
        get("/tracking") {
            val carrier = call.request.queryParameters["carrier"] ?: return@get call.respondText(
                "택배사를 입력해 주세요.",
                status = HttpStatusCode.BadRequest
            )
            val invoiceNumber = call.request.queryParameters["number"] ?: return@get call.respondText(
                "송장번호를 입력해 주세요.",
                status = HttpStatusCode.BadRequest
            )
            val tracking = LogisticsRoutingResponse.from(trackerDeliveryClient(carrier, invoiceNumber))

            call.respond(tracking)
        }
    }
}

package com.tistory.katfun.logistics

import com.tistory.katfun.logistics.models.TrackerDeliveryCarrier
import com.tistory.katfun.logistics.models.TrackerDeliveryPerson
import com.tistory.katfun.logistics.models.TrackerDeliveryProgress
import com.tistory.katfun.logistics.models.TrackerDeliveryResponse
import com.tistory.katfun.utilities.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME

@Serializable
data class LogisticsRoutingResponse(
    val from: LogisticsRoutingCustomer,
    val to: LogisticsRoutingCustomer,
    val carrier: LogisticsRoutingCarrier,
    val status: LogisticsStatus,
    val statusName: String,
    val progresses: List<LogisticsRoutingProgress>
) {
    companion object {
        fun from(response: TrackerDeliveryResponse): LogisticsRoutingResponse {
            val status = LogisticsStatus.valueOf(response.state.id.uppercase())
            return LogisticsRoutingResponse(
                from = LogisticsRoutingCustomer.from(response.from),
                to = LogisticsRoutingCustomer.from(response.to),
                carrier = LogisticsRoutingCarrier.from(response.carrier),
                status = status,
                statusName = status.description,
                progresses = response.progresses.map { progress ->
                    LogisticsRoutingProgress.from(progress)
                }
            )
        }
    }
}

@Serializable
data class LogisticsRoutingCustomer(
    val name: String,
    @Serializable(with = LocalDateTimeSerializer::class) val time: LocalDateTime?
) {
    companion object {
        fun from(customer: TrackerDeliveryPerson): LogisticsRoutingCustomer {
            with(customer) {
                return LogisticsRoutingCustomer(
                    name = name,
                    time = time?.let { LocalDateTime.parse(it, ISO_OFFSET_DATE_TIME) }
                )
            }
        }
    }
}

@Serializable
data class LogisticsRoutingCarrier(
    val id: String,
    val name: String,
    val tel: String
) {
    companion object {
        fun from(carrier: TrackerDeliveryCarrier): LogisticsRoutingCarrier {
            with(carrier) {
                return LogisticsRoutingCarrier(
                    id = id,
                    name = name,
                    tel = tel
                )
            }
        }
    }
}

@Serializable
data class LogisticsRoutingProgress(
    @Serializable(with = LocalDateTimeSerializer::class) val time: LocalDateTime,
    val status: LogisticsStatus,
    val statusName: String,
    val locationName: String,
    val description: String
) {
    companion object {
        fun from(progress: TrackerDeliveryProgress): LogisticsRoutingProgress {
            val status = LogisticsStatus.valueOf(progress.status.id.uppercase())
            return LogisticsRoutingProgress(
                time = LocalDateTime.parse(progress.time, ISO_OFFSET_DATE_TIME),
                status = status,
                statusName = status.description,
                locationName = progress.location.name,
                description = progress.description
            )
        }
    }
}

enum class LogisticsStatus(val description: String) {
    IN_TRANSIT("상품이동중"),
    AT_PICKUP("상품인수"),
    OUT_FOR_DELIVERY("배송출발"),
    DELIVERED("배송완료");
}

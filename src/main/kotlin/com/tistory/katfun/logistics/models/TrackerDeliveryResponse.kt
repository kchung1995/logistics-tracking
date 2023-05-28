package com.tistory.katfun.logistics.models

import kotlinx.serialization.Serializable

@Serializable
data class TrackerDeliveryResponse(
    val from: TrackerDeliveryPerson,
    val to: TrackerDeliveryPerson,
    val state: TrackerDeliveryStatus,
    val progresses: List<TrackerDeliveryProgress>,
    val carrier: TrackerDeliveryCarrier
)

@Serializable
data class TrackerDeliveryPerson(
    val name: String,
    val time: String?
)

@Serializable
data class TrackerDeliveryStatus(
    val id: String,
    val text: String
)

@Serializable
data class TrackerDeliveryProgress(
    val time: String,
    val status: TrackerDeliveryStatus,
    val location: TrackerDeliveryLocation,
    val description: String
)

@Serializable
data class TrackerDeliveryLocation(
    val name: String
)

@Serializable
data class TrackerDeliveryCarrier(
    val id: String,
    val name: String,
    val tel: String
)

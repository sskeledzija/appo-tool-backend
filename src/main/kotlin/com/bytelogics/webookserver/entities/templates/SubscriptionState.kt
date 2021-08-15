package com.bytelogics.webookserver.entities.templates

enum class SubscriptionState(val state: String) {
    PENDING("requested"),
    SUBSCRIBED("subscribed"),
    DECLINED("declined"),
    CANCELLED("canceled")
}
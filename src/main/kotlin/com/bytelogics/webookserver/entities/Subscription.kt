package com.bytelogics.webookserver.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document
data class Subscription(@Id val id: String = UUID.randomUUID().toString(),
                        val expiryDate: Instant,
                        val autosubscribe: Boolean,
                        val subscriptionType: String /*enum*/,
                        val paymentMethod: String /* PaymentMethod*/
)

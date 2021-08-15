package com.bytelogics.webookserver.entities.templates

import org.springframework.data.annotation.Id
import java.time.Instant
import java.util.*

class Review(@Id val id: String = UUID.randomUUID().toString(),
             val date: Instant,
             val reviewer: String,
             val reviewerId: UUID,
             val comment: String?,
             val rate: Rates,
             val recommend: Boolean?) {
}
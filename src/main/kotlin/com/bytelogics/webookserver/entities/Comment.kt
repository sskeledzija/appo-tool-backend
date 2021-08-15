package com.bytelogics.webookserver.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document
data class Comment(@Id val id: String = UUID.randomUUID().toString(),
                   val comment: String,
                   val commentDate: Instant)
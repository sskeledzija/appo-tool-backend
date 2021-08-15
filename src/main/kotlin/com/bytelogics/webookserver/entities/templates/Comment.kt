package com.bytelogics.webookserver.entities.templates

import org.springframework.data.annotation.Id
import java.time.Instant
import java.util.*

/* in first step we make comments simple */
class Comment(@Id val id: String = UUID.randomUUID().toString(),
              val commentator: String,
              val comment: String,
              val date: Instant) {

}
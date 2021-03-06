package com.bytelogics.webookserver.entities.templates

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class AppointmentAvailability(//val id: String = UUID.randomUUID(),
                              var start: LocalTime,
                              var end: LocalTime,
                              val operatorShortInfo: String,    // must not contain any sensitive data
                              val operatorId: String?, // TODO create operator entity - person who does the service
)
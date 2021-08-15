package com.bytelogics.webookserver.entities.templates

import java.time.LocalTime
import java.util.*

class AppointmentSlot(val id: String = UUID.randomUUID().toString(),
                      var start: LocalTime,
                      var end: LocalTime,
                      val userShortInfo: String,
                      val userId: String,
                      var seats: Int?,
                      var confirmed: Boolean?,
                      var cancelled: Boolean?,
                      var services: List<AppointmentServices>?,
                      var comments: List<Comment>?)  // dedicated method/endpoint must be used to insert comments.
                                                // Could also contain events, e.g. time of cancellation

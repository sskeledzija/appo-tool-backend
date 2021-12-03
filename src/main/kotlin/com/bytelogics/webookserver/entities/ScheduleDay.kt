package com.bytelogics.webookserver.entities

import com.bytelogics.webookserver.entities.templates.AppointmentAvailability
import com.bytelogics.webookserver.entities.templates.AppointmentSlot
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*

//@Document
class ScheduleDay(@Id val id: String = UUID.randomUUID().toString(),
      //            var bookingEntityId: String,
                  val date: LocalDate, // day
                  var maxSeats: Int?,
                  var dailyNotes: String?,
                  var availabilities: List<AppointmentAvailability>?,
                  var appointments: MutableList<AppointmentSlot>?
)
package com.bytelogics.webookserver.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
data class MonthlySchedule(@Indexed(unique=true)
                              @Id val id: String, // bookingentity ID + mmYYYY
                           val start: Instant,
                           val end: Instant?,
                           val userEntityId: String,
                           @DBRef val scheduleDays: List<ScheduleDay>?,    // generated from shifts and absences

)
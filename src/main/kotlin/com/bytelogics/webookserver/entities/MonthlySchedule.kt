package com.bytelogics.webookserver.entities

import com.bytelogics.webookserver.entities.templates.AbsenceDay
import com.bytelogics.webookserver.entities.templates.Holiday
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.time.LocalDate
import java.util.*

@Document
data class MonthlySchedule(@Indexed(unique=true)
                           @Id val id: String = UUID.randomUUID().toString(),
                           @DBRef val template: ShiftTemplate,
                           val createDate: Instant?,
                           val start: LocalDate,
                           val end: LocalDate,
                           val absences: List<AbsenceDay>?,
                           //val holidays: List<Holiday>?,   // if day is a holiday
                           var scheduleDays: MutableList<ScheduleDay>?    // generated from shifts and absences

)
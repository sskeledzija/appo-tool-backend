package com.bytelogics.webookserver.entities.templates;

import java.util.*

import org.springframework.data.annotation.Id;
import java.time.Instant
import java.time.LocalDate

class AbsenceDay(@Id val id: String = UUID.randomUUID().toString(),
                 val day: LocalDate,
                 val type: AbsenceType,
                 val description: String,
                 val requestedDate: Instant,
                 val approvedDate: Instant,
                 val approvedBy: Instant){

    enum class AbsenceType { VACATION, HOLIDAY, SICK_LEAVE, OTHER }
}

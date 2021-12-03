package com.bytelogics.webookserver.entities.templates;

import java.util.*

import org.springframework.data.annotation.Id;
import java.time.Instant
import java.time.LocalDate

class AbsenceRequest(@Id val id: String = UUID.randomUUID().toString(),
                     val from: LocalDate,
                     val to: LocalDate,
                     val type: AbsenceType,
                     val description: String,
                     val requestedDate: Instant,
                     val updateDate: Instant?,
                     val updatedBy: Instant?,
                     val approved: Boolean?){

    enum class AbsenceType { VACATION, HOLIDAY, SICK_LEAVE, OTHER }
}

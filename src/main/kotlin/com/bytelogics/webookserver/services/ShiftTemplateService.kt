package com.bytelogics.webookserver.services

import com.bytelogics.webookserver.Utils
import com.bytelogics.webookserver.entities.ScheduleDay
import com.bytelogics.webookserver.entities.templates.AppointmentAvailability
import com.bytelogics.webookserver.entities.templates.OpenPeriod
import com.bytelogics.webookserver.entities.templates.ShiftTemplate
import com.bytelogics.webookserver.exceptions.EntityNotFoundException
import com.bytelogics.webookserver.repositories.dao.AppointmentDayImpl
import com.bytelogics.webookserver.repositories.dao.UserEntityImpl
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Service
class ShiftTemplateService(val userEntity: UserEntityImpl,
                           val appointmentDayImpl: AppointmentDayImpl) {

    fun createBookingDays(bookingEntityId: String, templateId: String, fromDate: LocalDate, toDate: LocalDate): MutableList<ScheduleDay>? {
        val bookingEntity = userEntity.getBookingEntity(bookingEntityId)

        if (bookingEntity.isEmpty) {
            throw EntityNotFoundException("Booking entity with provided ID does not exist")
        }

        val template = bookingEntity.get().shiftTemplates?.find { it.id == templateId }

        template?: throw EntityNotFoundException("Template with provided ID does not exist")

        val days =  createDaysByTemplate(fromDate, toDate, template, bookingEntityId)//.workingHours?.forEach{ createDays(fromDate, toDate, it) }

        return appointmentDayImpl.createBookingDays(fromDate, toDate, bookingEntityId, days)
    }

    fun createDaysByTemplate( fromDate: LocalDate, toDate: LocalDate, template: ShiftTemplate, bookingEntityId: String): MutableList<ScheduleDay> {

        val days = Utils.daysBetweenDates(fromDate, toDate)
        var bookingDays = mutableListOf<ScheduleDay>()
        template.workingHours?.forEach { hours ->
            days?.forEach {
                when (Utils.dayName(it)) {
                    "monday" -> bookingDays.add(createDay(it, hours.monday, template, bookingEntityId))
                    "tuesday" -> bookingDays.add(createDay(it, hours.tuesday, template, bookingEntityId))
                    "wednesday" -> bookingDays.add(createDay(it, hours.wednesday, template, bookingEntityId))
                    "thursday" -> bookingDays.add(createDay(it, hours.thursday, template, bookingEntityId))
                    "friday" -> bookingDays.add(createDay(it, hours.friday, template, bookingEntityId))
                    "saturday" -> bookingDays.add(createDay(it, hours.saturday, template, bookingEntityId))
                    "sunday" -> bookingDays.add(createDay(it, hours.sunday, template, bookingEntityId))
                }
            }
        }

        return bookingDays.filter { !it.availabilities.isEmpty() }.toCollection(mutableListOf())
    }

    fun createDay(date: LocalDate, hours: List<OpenPeriod>?, template: ShiftTemplate, bookingEntityId: String): ScheduleDay {

        //hours ?: Unit
        return ScheduleDay(UUID.randomUUID().toString(), bookingEntityId, date, template.maxNrOfSeats, "",
            getAvailabilities(date, hours?: emptyList()), mutableListOf()
        )
    }

    fun getAvailabilities(date: LocalDate, hours: List<OpenPeriod>): List<AppointmentAvailability> {
        return hours.map {
            AppointmentAvailability(
                UUID.randomUUID(),
                LocalTime.parse(it.from),
                LocalTime.parse(it.to),
                "",
                UUID.randomUUID()
            )
        }.toCollection(arrayListOf())
    }
}
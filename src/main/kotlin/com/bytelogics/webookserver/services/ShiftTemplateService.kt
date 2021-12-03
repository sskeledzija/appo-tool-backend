package com.bytelogics.webookserver.services

import com.bytelogics.webookserver.Utils
import com.bytelogics.webookserver.entities.ScheduleDay
import com.bytelogics.webookserver.entities.ShiftTemplate
import com.bytelogics.webookserver.entities.templates.AppointmentAvailability
import com.bytelogics.webookserver.entities.templates.OpenPeriod
import com.bytelogics.webookserver.exceptions.EntityNotFoundException
import com.bytelogics.webookserver.repositories.dao.AppointmentDayImpl
import com.bytelogics.webookserver.repositories.dao.ShiftTemplateImpl
import com.bytelogics.webookserver.repositories.dao.UserEntityImpl
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Service
class ShiftTemplateService(private val userEntity: UserEntityImpl,
                           private val appointmentDayImpl: AppointmentDayImpl,
                           private val shiftTemplateImpl: ShiftTemplateImpl) {
/*
    fun createBookingDays(userEntityId: String, templateId: String, fromDate: LocalDate, toDate: LocalDate): MutableList<ScheduleDay>? {
        val maybeUserEntity = userEntity.getUserEntity(userEntityId)

        if (maybeUserEntity.isEmpty) {
            throw EntityNotFoundException("Booking entity with provided ID does not exist")
        }

        val template = shiftTemplateImpl.getById(templateId) //bookingEntity.get().shiftTemplates?.find { it.id == templateId }

        val days =  createDaysByTemplate(fromDate, toDate, template, userEntityId)//.workingHours?.forEach{ createDays(fromDate, toDate, it) }

        return appointmentDayImpl.createBookingDays(fromDate, toDate, userEntityId, days)
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
        return ScheduleDay(bookingEntityId, date, template.maxNrOfSeats, "",
            getAvailabilities(date, hours?: emptyList()), mutableListOf()
        )
    }

    fun getAvailabilities(date: LocalDate, hours: List<OpenPeriod>): List<AppointmentAvailability> {
        return hours.map {
            AppointmentAvailability(

                LocalTime.parse(it.from),
                LocalTime.parse(it.to),
                "",
                UUID.randomUUID().toString()
            )
        }.toCollection(arrayListOf())
    } */
}
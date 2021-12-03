package com.bytelogics.webookserver.controllers

import com.bytelogics.webookserver.entities.Appointment
import com.bytelogics.webookserver.entities.MonthlySchedule
import com.bytelogics.webookserver.entities.ScheduleDay
import com.bytelogics.webookserver.entities.templates.AppointmentSlot
import com.bytelogics.webookserver.entities.templates.UIEvents
import com.bytelogics.webookserver.repositories.dao.MonthlyScheduleImpl
import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@CrossOrigin
@RestController
@RequestMapping("/appos")
class AppointmentsController(val monthlySchedule: MonthlyScheduleImpl) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun getScheduleEvents(@RequestBody request: GetScheduleEventsRequest): UIEvents {
        logger.info("######## Get schedule events .....$request")

        return monthlySchedule.getScheduleEvents(request.templateIds, request.from, request.to, true)
    }

    /**
     * WORKING DAYS
     */
    /*
    @PostMapping("/{templateId}/working-days")
    fun createWorkingDays(@PathVariable entityId: String,
                          @RequestParam("templateId") templateId: String,
                          @RequestParam("from") @DateTimeFormat(pattern = "dd.MM.yyyy") fromDate: LocalDate,
                          @RequestParam("to") @DateTimeFormat(pattern = "dd.MM.yyyy") toDate: LocalDate ): MutableList<ScheduleDay>? {
        logger.info("# Create working days for booking entity [$entityId], template ID: [$templateId], date: [$fromDate - $toDate]")
        return monthlySchedule.createBookingDays(entityId, templateId, fromDate, toDate)
    }
*/
    /*
    @GetMapping("/{entityId}/working-days")
    fun getWorkingDays(@PathVariable entityId: String,
                       @RequestParam("templateId") templateId: String,
                       @RequestParam("from") @DateTimeFormat(pattern = "dd.MM.yyyy") fromDate: LocalDate,
                       @RequestParam("to") @DateTimeFormat(pattern = "dd.MM.yyyy") toDate: LocalDate ): List<ScheduleDay>? {
        logger.info("# Create working days for booking entity [$entityId], template ID: [$templateId], date: [$fromDate - $toDate]")
        return monthlySchedule.getBookingDaysSorted(fromDate, toDate, entityId)
    }*/

    /*
    @PostMapping("/{entityId}/working-days/{dayId}")
    fun bookWorkingSlot(@PathVariable entityId: String,
                        @PathVariable dayId: String, @RequestBody appointmentSlot: AppointmentSlot
    ): ScheduleDay? {
        logger.info("# Create booking for booking entity [$entityId], day ID: [$dayId], booking data: [$appointmentSlot]")
        return appointmentDayImpl.bookSlot(entityId, dayId, appointmentSlot)
    }
*/

    class GetScheduleEventsRequest(val templateIds: List<String>, val from: LocalDate, val to: LocalDate,
        eventsOnly: Boolean)

}
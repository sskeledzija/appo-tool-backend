package com.bytelogics.webookserver.repositories.dao

import com.bytelogics.webookserver.entities.MonthlySchedule
import com.bytelogics.webookserver.entities.ScheduleDay
import com.bytelogics.webookserver.entities.ShiftTemplate
import com.bytelogics.webookserver.entities.templates.AppointmentAvailability
import com.bytelogics.webookserver.entities.templates.UIEvent
import com.bytelogics.webookserver.entities.templates.UIEvents
import com.bytelogics.webookserver.exceptions.EntityNotFoundException
import com.bytelogics.webookserver.repositories.IMonthlySchedule
import com.bytelogics.webookserver.repositories.IShiftTemplate
import org.apache.tomcat.util.json.JSONParser
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.HashMap

@Service
class MonthlyScheduleImpl (val db: IMonthlySchedule, val shiftTemplate: IShiftTemplate) {

    private val logger = LoggerFactory.getLogger(javaClass)
    private var templates = HashMap<String, ShiftTemplate>();
    /*
    we can ask for more schedules at a same time, to reduce number of calls in case of multiple
    schedules overview
     */
    fun getScheduleEvents(templateIds: List<String>, from: LocalDate, to: LocalDate, eventsOnly: Boolean): UIEvents {
        //var templates = shiftTemplate.getTemplatesByIds(templateIds);

        var events: UIEvents = UIEvents(mutableListOf(), mutableListOf())
        val days = getDaysForTemplates(templateIds, from, to);

        /* generate days*/
        if (!eventsOnly) {
            //TODO merge days from all schedules (availabilities, absences, ...)
        }

        /* generate events */
        days.forEach{ day ->
            day.appointments?.forEach { appo ->
                events.events?.add(

                    UIEvent(UUID.randomUUID().toString(),
                    day.id,
                    appo.start,
                        appo.end,
                        appo.start.format(DateTimeFormatter.ISO_LOCAL_TIME),
                        appo.end.format(DateTimeFormatter.ISO_LOCAL_TIME),
                        appo.userShortInfo, // should contain also entity info like personel names
                        "", // to be defined
                    UIEvent.EventDisplay.AUTO,
                        true,
                        listOf(), // to be defined
                        "",
                        "",
                        "",
                        "",
                        false
                    )
                )
            }
        }


        println("################# days:  ${days.size}")
        return events;
    }

    fun getDaysForTemplates(templateIds: List<String>, from: LocalDate, to: LocalDate) : List<ScheduleDay> {

        val schedules = getSchedulesForDates(templateIds, from, to);

        val totalDays = mutableListOf<ScheduleDay>()
        schedules.forEach { month ->
            //extract days
            month.scheduleDays?.forEach { d -> if (isInRange(d.date, from, to)) totalDays.add(d) }
            //logger.info("############### TEMPLATES it: $it")
        }
        return totalDays;
    }

    fun isInRange(date: LocalDate, from: LocalDate, to: LocalDate): Boolean {
        return date.dayOfMonth == from.dayOfMonth && date.monthValue == from.monthValue ||
                date.dayOfMonth == to.dayOfMonth && date.monthValue == to.monthValue ||
                date.isBefore(to) && date.isAfter(from)
    }

    /* schedules must cover provided dates. That means if dates are in range of 3 months - we need at least 3 schedules.
    if schedule does not exist - create new one.
     */
    private fun getSchedulesForDates(templateIds: List<String>, from: LocalDate, to: LocalDate): List<MonthlySchedule> {

        val existingSchedules = db.findAllForDatesAndTemplates(templateIds, from, to).toMutableList()

        var missingSchedules = mutableListOf<MonthlySchedule>()
            // check if there are missing schedules
        templateIds.forEach { tid ->
            missingSchedules = checkMissingSchedules(tid, from, to, existingSchedules)
        }

        if (missingSchedules.isNotEmpty()) {
            missingSchedules = db.saveAll(missingSchedules);
            existingSchedules.addAll(missingSchedules)
        }
        return existingSchedules
    }

    private fun checkMissingSchedules(tid: String, from: LocalDate, to: LocalDate,
                                      existingSchedules: List<MonthlySchedule >): MutableList<MonthlySchedule> {
        val missingSchedules = mutableListOf<MonthlySchedule>()
        if (!existingSchedules.any { es -> es.template.id == tid && es.start.isBefore(from) && es.end.isAfter(from)}) {
            missingSchedules.add(createMonthlySchedule(tid, from))
        }

        if (to.monthValue != from.monthValue && !existingSchedules.any { es -> es.template.id == tid && es.start.isBefore(to) && es.end.isAfter(to)}) {
            missingSchedules.add(createMonthlySchedule(tid, to))
        }
        return missingSchedules;
    }

    private fun createMonthlySchedule(tid: String, from: LocalDate): MonthlySchedule {

        if (!templates.containsKey(tid)) {
            val fetchedTemplate = shiftTemplate.findById(tid)
            if (fetchedTemplate.isEmpty) {
                throw  EntityNotFoundException("template with ID $tid is not found.")
            }
            templates[tid] = fetchedTemplate.get()    // result should be only one!
        }
        val template = templates[tid];

        val schedule = template?.let { MonthlySchedule(UUID.randomUUID().toString(), it, Instant.now(),
            from.with(TemporalAdjusters.firstDayOfMonth()), from.with(TemporalAdjusters.lastDayOfMonth()),
        mutableListOf(), mutableListOf() ) }
        schedule!!

        // generate days
        val days = schedule.start.datesUntil(schedule.end.plusDays(1)).collect(Collectors.toList());

        schedule.scheduleDays = days.map { day ->
            // TODO set holiday
            // TODO set other absences
            // todo check template valid dates
             ScheduleDay(
                UUID.randomUUID().toString(),
                day,
                template.maxNrOfSeats,
                "some notes",
                getAvailabilities(template, day),
                mutableListOf()
            )
        }.toMutableList()

        return schedule;
    }

    private fun getAvailabilities(template: ShiftTemplate, day: LocalDate): List<AppointmentAvailability>? {
       return template.workingHours.getPeriodsForDay(day.dayOfWeek.name.lowercase())?.map { period ->  // list of dates [{from: 10:00, to: 12:00}, {..}]
            AppointmentAvailability(LocalTime.parse(period.from),
                LocalTime.parse(period.to), template.defaultOperators.toString(), "")
        }
    }

}
package com.bytelogics.webookserver.entities.templates

import org.springframework.data.mongodb.core.query.where
import java.time.Instant

class WorkingHours(var validFrom: Instant,
                   var validUntil: Instant?,
                   var monday: List<OpenPeriod>?,   // can include pause, e.g. 1. 10:00-12:30, 2. 13:00-16:00
                   var tuesday: List<OpenPeriod>?,
                   var wednesday: List<OpenPeriod>?,
                   var thursday: List<OpenPeriod>?,
                   var friday: List<OpenPeriod>?,
                   var saturday: List<OpenPeriod>?,
                   var sunday: List<OpenPeriod>?) {

    fun getPeriodsForDay(day: String): List<OpenPeriod>? {
        return when (day) {
            "monday" -> monday
            "tuesday" -> tuesday
            "wednesday" -> wednesday
            "thursday" -> thursday
            "friday" -> friday
            "saturday" -> saturday
            "sunday" -> sunday
            else -> {
                listOf()
            }

        }
    }
}
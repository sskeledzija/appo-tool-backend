package com.bytelogics.webookserver.entities.templates

import java.time.LocalDate
import java.time.LocalTime

/*
Will be used for re-schedule constraints (if there will be any)
 */
class UIEventPeriod(val startTime: LocalTime?,   // e.g. '10:00', can be also '2014-12-01T10:00:00'
                    val endTime: LocalTime?,    // e.g. '17:00'
    /* days of week. an array of zero-based day of week integers (0=Sunday)
     (Monday-Thursday in this example) */
                            val daysOfWeek: List<Int>?
                            )
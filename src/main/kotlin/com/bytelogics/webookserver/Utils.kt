package com.bytelogics.webookserver

import java.time.LocalDate
import java.util.stream.Collectors

object Utils {

    private val days = arrayOf ( "", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday" )

    @JvmStatic
    fun daysBetweenDates(from: LocalDate, to: LocalDate): MutableList<LocalDate>? {
        return from.datesUntil(to).collect(Collectors.toList())
    }

    @JvmStatic
    fun dayName(day: LocalDate) = days[day.dayOfWeek.value]

}
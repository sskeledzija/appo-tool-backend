package com.bytelogics.webookserver.entities.templates

import com.bytelogics.webookserver.entities.ScheduleDay
import java.time.LocalDate

/*
This object will contain all infos about availabilities and appointments, from start time to end time,
 which will be sent to UI. Backend should take care that all infos are correct and calculated from
 the latest schedule details (and for multiple templates at the same time too)
 */
class UIEvents(val events: MutableList<UIEvent>?,   // days converted to events - appointments
               val days: List<ScheduleDay>?,    // (merged) days with availabilities and absences

               )
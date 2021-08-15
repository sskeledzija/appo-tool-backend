package com.bytelogics.webookserver.entities.templates

import com.bytelogics.webookserver.entities.templates.DayOfWeekRules
import com.bytelogics.webookserver.entities.templates.WeekRules
import com.bytelogics.webookserver.entities.templates.WorkingHours
import org.springframework.data.annotation.Id
import java.time.Instant
import java.util.*

class ShiftTemplate(@Id val id: String = UUID.randomUUID().toString(),
                    var name: String,
                    var description: String?,
                    var validFrom: Instant?,    // validity start
                    var validTo: Instant?,      // validity end
                    var disabled: Boolean?,
                    var workingHours: MutableList<WorkingHours>?,
                    var dayOfWeekRules: DayOfWeekRules?,
                    var weekRules: WeekRules?,
                    var maxNrOfSeats: Int?, // at the same time. When booking slot, nr of free seats in that period must be taken in consideration
                    var defaultOperators: MutableList<String>?
)
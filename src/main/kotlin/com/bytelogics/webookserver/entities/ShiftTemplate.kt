package com.bytelogics.webookserver.entities

import com.bytelogics.webookserver.entities.templates.DayOfWeekRules
import com.bytelogics.webookserver.entities.templates.WeekRules
import com.bytelogics.webookserver.entities.templates.WorkingHours
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document
class ShiftTemplate(@Id val id: String = UUID.randomUUID().toString(),
                    @DBRef var entity: UserEntity,
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
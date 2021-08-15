package com.bytelogics.webookserver.entities.templates

import java.time.Instant
import java.util.*

class DayOfWeekRules(var description: String?,
                     var startingDate: Instant?,
                     var endingDate: Instant?,
                     var monday: DayRule = DayRule.EVERY,
                     var tuesday: DayRule = DayRule.EVERY,
                     var wednesday: DayRule = DayRule.EVERY,
                     var thursday: DayRule = DayRule.EVERY,
                     var friday: DayRule = DayRule.EVERY,
                     var saturday: DayRule = DayRule.NONE,
                     var sunday: DayRule = DayRule.NONE,) {

    enum class DayRule { EVERY, ODD, EVEN, NONE}

    fun passes(date: Date, rule: DayRule): Boolean {

        if (rule == DayRule.EVERY) return true
        // TODO
        return false
    }
}
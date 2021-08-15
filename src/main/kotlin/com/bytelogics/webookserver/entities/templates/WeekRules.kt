package com.bytelogics.webookserver.entities.templates

import java.time.Instant

class WeekRules(var description: String?,
                var startingDate: Instant?,
                var endingDate: Instant?,
                var switchOperators: WeekRule = WeekRule.NEVER,
                var mixOperators: WeekRule  = WeekRule.NEVER) {

    enum class WeekRule { EVERY, ODD, EVEN, NEVER}

    fun isOddDayOfWeek(date: Instant): Boolean {
        // TODO
        return false
    }
}
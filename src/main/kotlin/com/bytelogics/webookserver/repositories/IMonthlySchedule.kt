package com.bytelogics.webookserver.repositories

import com.bytelogics.webookserver.entities.MonthlySchedule
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.Instant
import java.time.LocalDate

interface IMonthlySchedule: MongoRepository<MonthlySchedule, String> {

    @Query("{ 'template.\$id' : {\$in: ?0}, " +
            "\$or: [{'start': {\$lt: ?1}, 'end': {\$gte: ?1}}," +
            "{'start': {\$lt: ?2}, 'end': {\$gte: ?2}}] }")
    fun findAllForDatesAndTemplates(templateIds: List<String>, from: LocalDate, to: LocalDate): List<MonthlySchedule>
}
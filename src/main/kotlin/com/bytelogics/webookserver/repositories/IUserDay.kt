package com.bytelogics.webookserver.repositories

import com.bytelogics.webookserver.entities.ScheduleDay
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.LocalDate
import java.util.*

interface IUserDay: MongoRepository<ScheduleDay, String>/*, QuerydslPredicateExecutor<Booker>*/ {

    @Query("{ 'date': {'\$gte': ?0, '\$lt': ?1}, 'bookingEntityId' : ?2}")
    fun getBookingDaysRange(fromDate: LocalDate, toDate: LocalDate, bookingEntityId: String) : MutableList<ScheduleDay>

    @Query("{'bookingEntityId': ?0, 'id' : ?1}")
    fun getBookingDay(bookingEntityId: String, dayId: String): Optional<ScheduleDay>
}
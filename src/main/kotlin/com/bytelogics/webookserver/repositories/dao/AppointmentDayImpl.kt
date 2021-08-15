package com.bytelogics.webookserver.repositories.dao

import com.bytelogics.webookserver.entities.ScheduleDay
import com.bytelogics.webookserver.entities.templates.AppointmentSlot
import com.bytelogics.webookserver.exceptions.EntityNotFoundException
import com.bytelogics.webookserver.repositories.IUserDay
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AppointmentDayImpl (val db: IUserDay){

    /**
     * BOOKING DAYS
     * */
    fun createBookingDays(fromDate: LocalDate, toDate: LocalDate, bookingEntityId: String, scheduleDays: List<ScheduleDay>): MutableList<ScheduleDay> {

        println("############# New: ${scheduleDays.size}")

        val existingBookingDays = getBookingDays(fromDate, toDate, bookingEntityId)

        println("############# Existing: ${existingBookingDays.size}")

        if (existingBookingDays.isEmpty()) return db.saveAll(scheduleDays)

        // if some data already exists, update already booked values (if exist)
        scheduleDays.forEach{ newDay ->
            existingBookingDays.filter { localDateMatch(it.date, newDay.date) }.forEach{ newDay.appointments.addAll(it.appointments) }
        }

        println("############# Updated: ${scheduleDays.size}")

        // now remove old ones,
        deleteAll(existingBookingDays)

        // and insert new ones
        return db.saveAll(scheduleDays)
    }

    fun localDateMatch(date1: LocalDate, date2: LocalDate) = date1.dayOfMonth == date2.dayOfMonth && date1.month == date2.month

    fun getBookingDay(id: String) = db.findById(id)

    fun getBookingDays(fromDate: LocalDate, toDate: LocalDate, bookingEntityId: String)
            = db.getBookingDaysRange(fromDate, toDate, bookingEntityId)

    fun getBookingDaysSorted(fromDate: LocalDate, toDate: LocalDate, bookingEntityId: String): List<ScheduleDay> =
        getBookingDays(fromDate, toDate, bookingEntityId).also { it.sortBy {o -> o.date }; return it}

    /**
     * BOOKING SLOTS
     * */
    fun bookSlot(bookingEntityId: String, dayId: String, appointmentSlot: AppointmentSlot): ScheduleDay {

        val day = db.getBookingDay(bookingEntityId, dayId)

        if (day.isEmpty) throw EntityNotFoundException("Booking day not found")

        // TODO if (!bookingFits(bookingSlot)) throw CannotInsertBookingException()

        appointmentSlot.confirmed = false
        day.get().appointments.add(appointmentSlot)

        return db.save(day.get())

    }

    fun deleteAll(scheduleDays: List<ScheduleDay>) = db.deleteAll(scheduleDays)
}
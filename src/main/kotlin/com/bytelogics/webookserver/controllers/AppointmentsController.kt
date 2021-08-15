package com.bytelogics.webookserver.controllers

import com.bytelogics.webookserver.entities.Appointment
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/booking")
class AppointmentsController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun createBooking(@RequestBody appointment: Appointment) {
        logger.info("######## create booking ....." + appointment)
        // do something
    }

//    @GetMapping("/{id}")
//    fun getBookingEntity(@PathVariable id: String): Booking {
//        println("############ get boooking entity " + id)
//        return Booking(ObjectId(), )
//    }
}
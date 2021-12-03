package com.bytelogics.webookserver.entities.templates;

import java.util.*

import org.springframework.data.annotation.Id;
import java.time.Instant
import java.time.LocalDate

class Holiday(@Id val id: String = UUID.randomUUID().toString(),
              val from: LocalDate,
              val to: LocalDate,
              val description: String){

}

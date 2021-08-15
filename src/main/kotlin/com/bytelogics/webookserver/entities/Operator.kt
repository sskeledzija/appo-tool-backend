package com.bytelogics.webookserver.entities

import com.bytelogics.webookserver.entities.templates.*
import com.bytelogics.webookserver.entities.templates.Comment
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document
class Operator(@Id val id: String = UUID.randomUUID().toString(),
               var name: String,
               var lastName: String,
               var address: Address,
               var contactData: ContactData?,
               val operatorShortInfo: String?,    // must not contain any sensitive data
               var activeFromDate: Instant,
               var activeToDate: Instant?,
               var services: MutableList<AppointmentServices>?,
               var comments: MutableList<Comment>?,
               val reviews: MutableList<Review>?) {  // dedicated method/endpoint must be used to insert comments

   // @Transient val shortDescription: () -> String = {operatorShortInfo?: "$lastName, $name"}

}
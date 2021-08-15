package com.bytelogics.webookserver.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

// obsolete
@Document
data class Appointment(@Id val id: UUID = UUID.randomUUID(),
                       val user: User,
                       val userEntity: UserEntity,
                       val date: Instant,
                       val comment: Comment,
                       val approved: Boolean)
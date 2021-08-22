package com.bytelogics.webookserver.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class ActivityField(@Id val id: String = UUID.randomUUID().toString(),
                         @Indexed(unique=true)
                         val name: String,
                         @DBRef val user: User?,    // user can define own activity field
                         val description : String,
                         val rate: Int
)
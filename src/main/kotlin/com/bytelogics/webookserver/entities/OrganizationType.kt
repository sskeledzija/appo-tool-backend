package com.bytelogics.webookserver.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.util.*

@Document
data class OrganizationType(@Id val id: String = UUID.randomUUID().toString(),
                            @Indexed(unique=true)
                            val name: String,
                            val description: String,
                            val rate: Int)
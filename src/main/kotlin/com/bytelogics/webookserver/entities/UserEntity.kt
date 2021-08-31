package com.bytelogics.webookserver.entities

import com.bytelogics.webookserver.entities.templates.Address
import com.bytelogics.webookserver.entities.templates.Email
import com.bytelogics.webookserver.entities.templates.Phone
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class UserEntity (@Id val id: String = UUID.randomUUID().toString(),
                       @DBRef val user: User,
                       val name: String,
                       val description: String,
                       @DBRef val activityArea: ActivityField?,
                       @DBRef val organizationType: OrganizationType?,
                       val address: Address?,
                       val phone: Phone?,
                       val email: Email,
                      // var shiftTemplates: MutableList<ShiftTemplate>?
)
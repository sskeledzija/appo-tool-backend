package com.bytelogics.webookserver.entities

import com.bytelogics.webookserver.entities.templates.Address
import com.bytelogics.webookserver.entities.templates.Email
import com.bytelogics.webookserver.entities.templates.Phone
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document
data class User(@Id val id: String = UUID.randomUUID().toString(),
                val name: String,
                var lastName: String,
                var address: Address?,
                var phone: Phone?,
                var email: Email,
                var userShortInfo: String?,
                var dateCreated: Instant?,
                var confirmed: Boolean?,
                val gdprConsent: Boolean? /*TODO replaced with concrete consent object*/,
                @Transient var token: String?,
                @Transient var password: String?) {
}
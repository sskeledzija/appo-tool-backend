package com.bytelogics.webookserver.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document
class Credentials(@Id val id: String = UUID.randomUUID().toString(),
                  val emailAddress: String,
                  @DBRef var user: User,
                  var password: String?,
                  var token: String?,
                  var lastLogin: Instant?,
                  var lastLoginType: LoginType?,
                  var status: LoginStatus?,
                  var failedAttempts: Int?,
                  var passwordHistory: MutableList<String>?) {

    enum class LoginStatus { ACTIVE, LOCKED, PENDING }
    enum class LoginType { CREDENTIALS, TOKEN, GUEST}

}
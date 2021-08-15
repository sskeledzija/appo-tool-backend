package com.bytelogics.webookserver.repositories

import com.bytelogics.webookserver.entities.Credentials
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface ICredentials: MongoRepository<Credentials, String> {

    @Query("{ 'emailAddress' : ?0}")
    fun findByEmail(email: String): Optional<Credentials>

    @Query("{ 'token' : ?0}")
    fun findByToken(token: String): Optional<Credentials>
}
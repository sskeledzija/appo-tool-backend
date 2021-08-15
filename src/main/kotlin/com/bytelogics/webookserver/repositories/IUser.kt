package com.bytelogics.webookserver.repositories

import com.bytelogics.webookserver.entities.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface IUser: MongoRepository<User, String>/*, QuerydslPredicateExecutor<Booker>*/ {

    // in general there should be only 1 or 0 bookers with same email, but errors can happen therefore we must handle them...
    @Query("{ 'email.address' : ?0}")
    fun findOneByEmail(email: String): Optional<User>

    @Query("{ \$or: [ { 'name' : { \$regex: ?0 } }, " +
                    "{'lastName' : { \$regex: ?0 }}] }")
    fun findByFirstOrName(name: String): List<User>

}
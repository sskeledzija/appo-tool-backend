package com.bytelogics.webookserver.repositories

import com.bytelogics.webookserver.entities.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface IUserEntity: MongoRepository<UserEntity, String>/*, QuerydslPredicateExecutor<Booker>*/ {

    @Query("{ 'email' : ?0, confirmed: true}")
    fun findAllByEmail(email: String): Optional<List<UserEntity>>

    @Query("{ \$or: [ { 'name' : { \$regex: ?0, \$options: '-i'  } }, " +
                    "{'description' : { \$regex: ?0, \$options: '-i'  }}] }")
    fun findByNameOrDescription(name: String): List<UserEntity>

}
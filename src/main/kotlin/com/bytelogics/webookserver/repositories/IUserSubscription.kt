package com.bytelogics.webookserver.repositories

import com.bytelogics.webookserver.entities.UserSubscription
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface IUserSubscription: MongoRepository<UserSubscription, String>/*, QuerydslPredicateExecutor<Booker>*/ {

    @Query("{ 'userId' : ?0, 'status' : {\$ne: 'CANCELLED'}}")
    fun findAllBookerSubscriptions(bookerId: String): List<UserSubscription>

//    @Query("{ 'bookerId' : ?0, 'bookingEntity.id' : ?1, 'status' : 'SUBSCRIBED'}")
    @Query("{ 'userId' : ?0, 'entity.id' : ?1, 'status' : {\$ne: 'CANCELLED'}}")
    fun findActiveSubscriptionByBookerAndBookingEntity(bookerId: String, bookingEntityId: String): Optional<UserSubscription>
}
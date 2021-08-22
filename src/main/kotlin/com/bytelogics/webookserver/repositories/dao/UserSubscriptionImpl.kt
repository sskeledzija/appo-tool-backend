package com.bytelogics.webookserver.repositories.dao

import com.bytelogics.webookserver.entities.UserSubscription
import com.bytelogics.webookserver.entities.templates.SubscriptionRequest
import com.bytelogics.webookserver.entities.templates.SubscriptionState
import com.bytelogics.webookserver.exceptions.EntityAlreadyExistsException
import com.bytelogics.webookserver.exceptions.EntityMismatchException
import com.bytelogics.webookserver.exceptions.EntityNotFoundException
import com.bytelogics.webookserver.repositories.IUserSubscription
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserSubscriptionImpl (val db: IUserSubscription, val userEntityImpl: UserEntityImpl){

    fun createBookerSubscription(bookerId: String, bookingEntityId: String, subscriptionRequest: SubscriptionRequest): UserSubscription {

        if (findByBookerAndBookingEntity(bookerId, bookingEntityId).isPresent) {
            throw EntityAlreadyExistsException("Already active subscription exists")
        }

        val bookingEntity = userEntityImpl.getUserEntity(bookingEntityId)

        val bookerSubscription = UserSubscription(UUID.randomUUID().toString(), bookerId, bookingEntity.get(),
            Date().toInstant(), subscriptionRequest.requestComment, null, null, null, null, SubscriptionState.PENDING,
        null, Date().toInstant())

        return db.save(bookerSubscription)
    }

    fun subscribeBookerSubscription(bookerId: String, subscription: UserSubscription): UserSubscription {
        val subscriptionRequest = db.findById(subscription.id)

        if (subscriptionRequest.isEmpty) {
            throw EntityNotFoundException("Booker subscription not found")
        }

        if (subscriptionRequest.get().userId != bookerId) {
            throw EntityMismatchException("Booking entity does not belong to booker!")
        }

        subscriptionRequest.get().subscriptionDate = subscription.subscriptionDate
        subscriptionRequest.get().subscriptionExpiryDate = subscription.subscriptionExpiryDate
        subscriptionRequest.get().lastChangeDate = Date().toInstant()

        return updateSubscriptionStatus(subscriptionRequest.get(), SubscriptionState.SUBSCRIBED)
    }

    private fun updateSubscriptionStatus(subscription: UserSubscription, state: SubscriptionState): UserSubscription {
        subscription.status = state
        return db.save(subscription)
    }

    fun cancelBookerSubscription(bookerId: String, subscriptionId: String): UserSubscription {
        val bookerSubscription = db.findActiveSubscriptionByBookerAndBookingEntity(bookerId, subscriptionId)

        if (bookerSubscription.isEmpty) {
            throw EntityNotFoundException("Booker subscription not found")
        }

//        if (bookerSubscription.get().bookerId != bookerId) {
//            throw EntityMismatchException("Booking entity does not belong to booker!")
//        }

        bookerSubscription.get().subscriptionExpiryDate = Date().toInstant()
        bookerSubscription.get().lastChangeDate = Date().toInstant()

        return updateSubscriptionStatus(bookerSubscription.get(), SubscriptionState.CANCELLED)
    }

    fun getSubscriptionById(bookerId: String, subscriptionId: String): Optional<UserSubscription> {

        val subscription = db.findById(subscriptionId)

        if (subscription.isEmpty) {
            throw EntityNotFoundException("Booker subscription not found")
        }

        if (subscription.get().userId != bookerId) {
            throw EntityMismatchException("Subscription does not belong to booker")
        }

        return subscription
    }

    fun getBookerSubscriptions(bookerId: String): List<UserSubscription> {
        return db.findAllBookerSubscriptions(bookerId)
    }

    fun findByBookerAndBookingEntity(bookerId: String, bookingEntityId: String): Optional<UserSubscription> {
        return db.findActiveSubscriptionByBookerAndBookingEntity(bookerId, bookingEntityId)
    }

}
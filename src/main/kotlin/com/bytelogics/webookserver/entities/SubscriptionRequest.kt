package com.bytelogics.webookserver.entities

import com.bytelogics.webookserver.entities.templates.SubscriptionState
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document
data class SubscriptionRequest(@Id val id: String = UUID.randomUUID().toString(),
                               val bookerId: String /*Booker will be loaded on return to client*/,
                               val bookingEntityId: String?, /*BookingEntity will be loaded on return to client*/

                               var requestDate: Instant?,
                               val requestComment: String?,   // few booker details, for BEntity to recognize
                               var requestExpiryDate: Instant?,
                               var subscriptionDate: Instant?,
                               var subscriptionExpiryDate: Instant?,
                               val comment: String?, // (List of) comments should be separate entity connected to this entity
                               var status: SubscriptionState?,
                               var declineComment: String?,
                               var lastChangeDate: Instant?) //
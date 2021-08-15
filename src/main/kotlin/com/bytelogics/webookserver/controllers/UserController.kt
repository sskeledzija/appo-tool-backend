package com.bytelogics.webookserver.controllers

import com.bytelogics.webookserver.entities.User
import com.bytelogics.webookserver.entities.UserSubscription
import com.bytelogics.webookserver.entities.templates.SubscriptionRequest
import com.bytelogics.webookserver.repositories.dao.UserImpl
import com.bytelogics.webookserver.repositories.dao.UserSubscriptionImpl
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin
@RestController(value = "/users" )
@RequestMapping("/users")
class UserController(val userService: UserImpl,
                     val userSubscription: UserSubscriptionImpl) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun createBooker(@RequestBody user: User): ResponseEntity<User> {
        logger.info("## Create user: [$user]")

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user))
    }

    @PutMapping
    fun updateBooker(@RequestBody user: User): ResponseEntity<User> {
        logger.info("# Update user [$user]")
        val updateBooker = userService.updateBooker(user)
        // todo get etag
        return ResponseEntity.ok(updateBooker)
    }

    @GetMapping("/{id}")
    fun getBooker(@PathVariable id: String): ResponseEntity<User> {
        logger.info("# Get user by ID [$id]")
        val optUser: Optional<User> = userService.findBooker(id);
        return ResponseEntity.of(optUser)
    }

    @GetMapping
    fun findBookerByEmail(@RequestParam email: String): ResponseEntity<User> {
        logger.info("# Find user by email [$email]")
        val booker = userService.findOneByEmail(email)
        return ResponseEntity.of(booker)
    }

    @GetMapping("/find")
    fun findBookerByFirstOrLastName(@RequestParam name: String): ResponseEntity<List<User>> {
        logger.info("# Find by first or last name [$name]... ")
        return ResponseEntity.ok(userService.findByNameOrAddress(name))
    }

    /**
     * BOOKER SUBSCRIPTIONS
     * */
    @GetMapping("/{id}/subscriptions/{subscriptionId}")
    fun getBookerSubscription(@PathVariable id: String, @PathVariable subscriptionId: String): ResponseEntity<UserSubscription> {
        logger.info("# Get user subscription. Booker id: [$id], subscription id: [$subscriptionId]")
        val subscription = userSubscription.getSubscriptionById(id, subscriptionId);
        return ResponseEntity.of(subscription)
    }

    @GetMapping("/{id}/subscriptions")
    fun getBookerSubscriptions(@PathVariable id: String): ResponseEntity<List<UserSubscription>> {
        logger.info("# Get all user subscriptions. Booker id: [$id].")
        val subscriptions = userSubscription.getBookerSubscriptions(id);
        return ResponseEntity.ok(subscriptions)

    }

    @PostMapping("/{id}/subscriptions/{bookingEntityId}")
    fun createBookerSubscriptionRequest(@PathVariable id: String, @PathVariable bookingEntityId: String,
                                        @RequestBody subscription: SubscriptionRequest): ResponseEntity<UserSubscription> {
        logger.info("# Create user subscription. user id: [$id], booking entity id [$bookingEntityId] subscription data: [$subscription]")
        val userSubscription: UserSubscription = userSubscription.createBookerSubscription(id, bookingEntityId, subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSubscription)
    }

    @PostMapping("/{id}/subscriptions/subscribe")
    fun subscribeBookerSubscription(@PathVariable id: String, @RequestBody subscription: UserSubscription): ResponseEntity<UserSubscription> {
        logger.info("# Subscribe user subscription. User id: [$id], subscription data: [$subscription]")
        val userSubscription: UserSubscription = userSubscription.subscribeBookerSubscription(id, subscription);
        return ResponseEntity.ok(userSubscription)
    }

    @DeleteMapping("/{id}/subscriptions/{subscriptionId}")
    fun cancelBookerSubscription(@PathVariable id: String, @PathVariable subscriptionId: String,): ResponseEntity<UserSubscription> {
        logger.info("# Cancel user subscription. User id: [$id], subscription to be canceled: [$subscriptionId]")
        val bookerSubscription = userSubscription.cancelBookerSubscription(id, subscriptionId);
        return ResponseEntity.status(HttpStatus.GONE).body(bookerSubscription)
    }
}
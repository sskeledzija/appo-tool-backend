package com.bytelogics.webookserver.controllers

import com.bytelogics.webookserver.entities.User
import com.bytelogics.webookserver.entities.Credentials
import com.bytelogics.webookserver.repositories.dao.CredentialsImpl
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin
@RestController(value = "/login" )
@RequestMapping("/login")
class CredentialsController(val credentialsImpl: CredentialsImpl) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @CrossOrigin
    @PostMapping("/register")
    fun register(@RequestBody registerRequest: Credentials): ResponseEntity<User> {
        logger.info("## Create registration: [$registerRequest]")

        return ResponseEntity.status(HttpStatus.CREATED).body(credentialsImpl.register(registerRequest))
    }

    @CrossOrigin
    @PostMapping("/get")
    fun getBooker(@RequestBody request: GetRequest): ResponseEntity<Credentials> {
        logger.info("# Get credentials by email [$request]")
        val maybeCredentials: Optional<Credentials> = credentialsImpl.findCredentialsByEmail(request.email);
        return ResponseEntity.of(maybeCredentials)
    }

    @CrossOrigin
    @PostMapping("/change-password")
    fun changePassword(@RequestBody request: ChangePasswordRequest): ResponseEntity<String> {
        logger.info("# Change password [$request]")
        credentialsImpl.changePassword(request.email, currentPass = request.currentPass, newPass = request.newPass);
        return ResponseEntity.ok("Password changed")
    }

    @PostMapping
    fun login(@RequestBody request: LoginRequest): ResponseEntity<User> {
        logger.info("# Login user [$request]")
        return ResponseEntity.ok().body(credentialsImpl.login(request.email, password = request.password))
    }

    @CrossOrigin()
    @PostMapping("/token")
    fun loginWithToken(@RequestBody request: LoginWithTokenRequest): ResponseEntity<User> {
        logger.info("# Login user with token [$request]")
        return ResponseEntity.ok().body(credentialsImpl.loginWithToken(request.token))
    }

    class GetRequest(val email: String)
    class ChangePasswordRequest(val email: String, val currentPass: String, val newPass: String)
    class LoginRequest(val email: String, val password: String)
    class LoginWithTokenRequest(val token: String)

}
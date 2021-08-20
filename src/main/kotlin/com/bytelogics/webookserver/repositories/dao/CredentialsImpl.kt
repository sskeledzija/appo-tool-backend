package com.bytelogics.webookserver.repositories.dao

import com.bytelogics.webookserver.entities.User
import com.bytelogics.webookserver.entities.Credentials
import com.bytelogics.webookserver.exceptions.EntityAlreadyExistsException
import com.bytelogics.webookserver.exceptions.EntityMismatchException
import com.bytelogics.webookserver.exceptions.EntityNotFoundException
import com.bytelogics.webookserver.repositories.ICredentials
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service
import java.util.*

@Service
class CredentialsImpl (val db: ICredentials, val userImpl: UserImpl) {

    fun findCredentialsByEmail(email: String ): Optional<Credentials> = db.findByEmail(email)
    fun findCredentialsByToken(token: String ): Optional<Credentials> = db.findByToken(token)

    /* LOGIN */
    fun login(email: String, password: String): User {
        val userCreds = findCredentialsByEmail(email)

        if (userCreds.isEmpty) {
            throw EntityNotFoundException("Incorrect email")
        }

        if (userCreds.get().password != password) {
            throw EntityMismatchException("Incorrect password")
        }

        // update token on every login
       // userCreds.get().token = UUID.randomUUID().toString();
        userCreds.get().lastLogin = Date().toInstant()
        userCreds.get().lastLoginType = Credentials.LoginType.CREDENTIALS
        val updatedUserCreds = db.save(userCreds.get())

        updatedUserCreds.user.token = updatedUserCreds.token
        return updatedUserCreds.user
    }

    /* LOGIN WITH TOKEN */
    fun loginWithToken(token: String): User {
        val userCreds = findCredentialsByToken(token)

        if (userCreds.isEmpty) {
            throw EntityNotFoundException("Invalid token")
        }

        userCreds.get().lastLoginType = Credentials.LoginType.TOKEN
        userCreds.get().lastLogin = Date().toInstant()

        val updatedUserCreds = db.save(userCreds.get())

        updatedUserCreds.user.token = updatedUserCreds.token
        return updatedUserCreds.user
    }

    /* REGISTER */
    fun register(credentials: Credentials): User {
        credentials.failedAttempts = 0;
        credentials.status = Credentials.LoginStatus.PENDING
        // update token
        credentials.token = UUID.randomUUID().toString();
        credentials.user.token = credentials.token
        credentials.lastLogin = Date().toInstant()
        credentials.password = credentials.user.password

        val user = userImpl.findOneByEmail(credentials.emailAddress);
        if (user.isEmpty) {
            credentials.user = userImpl.create(credentials.user)
        } else
            credentials.user = userImpl.updateBooker(credentials.user)

        return db.save(credentials).user
    }

    /* CHANGE PASSWORD */
    fun changePassword(email: String, currentPass: String, newPass: String) {

        val credentials = findCredentialsByEmail(email);

        // non existing mail
        if (credentials.isEmpty) {
            throw EntityNotFoundException("Credentials not found")
        }

        // password does not match
        if (credentials.get().password != currentPass) {
            throw EntityMismatchException("Password is incorrect")
        }

        // pass already used. TODO should be checked last 3 passwords
        if (credentials.get().passwordHistory?.any { pass -> pass == newPass } == true) {
            throw EntityAlreadyExistsException("Password already used")
        }

        if (credentials.get().passwordHistory == null) {
            credentials.get().passwordHistory = mutableListOf(currentPass)
        }
        credentials.get().passwordHistory!!.add(currentPass)
        credentials.get().password = newPass

        db.save(credentials.get())
    }

    /* RESET PASSWORD
    * ...and send it to mail */
    fun resetPassword(email: String): String {
        val credentials = findCredentialsByEmail(email);

        // non existing mail
        if (credentials.isEmpty) {
            throw EntityNotFoundException("Credentials not found")
        }

        credentials.get().password = RandomStringUtils.random(10, true, true)

        return credentials.get().password.orEmpty()

    }

}
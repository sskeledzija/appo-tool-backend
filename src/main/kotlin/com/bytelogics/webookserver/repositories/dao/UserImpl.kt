package com.bytelogics.webookserver.repositories.dao

import com.bytelogics.webookserver.entities.User
import com.bytelogics.webookserver.exceptions.EntityAlreadyExistsException
import com.bytelogics.webookserver.repositories.IUser
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserImpl (val db: IUser){

    fun updateBooker(user: User): User {
        val existingBooker = getUser(user.id)

        if (existingBooker.isPresent) {
            existingBooker.get().address = user.address
            existingBooker.get().email = user.email
            existingBooker.get().lastName = user.lastName
            return saveBooker(existingBooker.get())
        }

        return saveBooker(user)
    }

    // should be never used
    fun findUsers(): List<User> = db.findAll()

    fun findUser(id: String): Optional<User> = db.findById(id)

    fun create(user: User): User {

        val existingUser = findOneByEmail(user.email.address)

        if (existingUser.isPresent) {
            throw EntityAlreadyExistsException("Booker already exists")
        }

        user.dateCreated = Date().toInstant()
        user.token = UUID.randomUUID().toString()



        val user = saveBooker(user)

//        var credentials = credentialsImpl.register(Credentials(UUID.randomUUID().toString(),
//        booker.email.address, user, booker.password, booker.token!!, Date().toInstant(), null, Credentials.LoginStatus.ACTIVE,
//        0, mutableListOf()
//        ))

        return user
    }

    fun findOneByEmail(email: String):Optional<User> {
        return db.findOneByEmail(email)
    }

    fun findByNameOrAddress(name: String): List<User> {
        return db.findByFirstOrName("^(?=.*$name)")
    }

    private fun saveBooker(user: User):  User {
        return db.save(user)
    }

    private val getUser: (String) -> Optional<User> = { id: String -> db.findById(id)}
}
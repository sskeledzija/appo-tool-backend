package com.bytelogics.webookserver.repositories.dao

import com.bytelogics.webookserver.entities.UserEntity
import com.bytelogics.webookserver.repositories.IUserEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserEntityImpl (val db: IUserEntity){

    fun findUserEntities(): List<UserEntity> = db.findAll()

    fun getUserEntity(id: String): Optional<UserEntity> = db.findById(id)

    fun create(userEntity: UserEntity): UserEntity {
        return db.save(userEntity)
    }
/*
    fun findAllByEmail(email: String): List<UserEntity> {
        return db.findAllByEmail(email).get()
    }*/
    fun findAllByUserId(userid: String): Optional<List<UserEntity>> {
    val results =     db.findAllByUserId(userid)
    return db.findAllByUserId(userid)
    }

    fun findByNameOrDescription(key: String): List<UserEntity> {
        return db.findByNameOrDescription("^(?=.*$key)")
    }

//    fun createShiftTemplate(bookingEntityId: String, shiftTemplate: ShiftTemplate) {
//        var bookingEntity = getUserEntity(id = bookingEntityId)

//        if (bookingEntity.isEmpty) {
//            throw EntityNotFoundException("Booking entity not found")
//        }

//        bookingEntity.get().shiftTemplates = bookingEntity.get().shiftTemplates ?: mutableListOf<ShiftTemplate>()

//        bookingEntity.get().shiftTemplates!!.add(shiftTemplate)

//        db.save(bookingEntity.get())
//    }

//    fun updateShiftTemplate(bookingEntityId: String, templateId: String, shiftTemplate: ShiftTemplate) {
//        val bookingEntity = getUserEntity(id = bookingEntityId)

//        if (bookingEntity.isEmpty) {
//            throw EntityNotFoundException("Booking entity not found")
//        }

        // TODO find some nicer solution for this :)
//        bookingEntity.get().shiftTemplates?.forEachIndexed { index, temp ->
//            temp.takeIf { it.id == templateId }?.let {
//                bookingEntity.get().shiftTemplates!![index] = ShiftTemplate(templateId, shiftTemplate.name, shiftTemplate.description,
//                    shiftTemplate.validFrom, shiftTemplate.validTo, shiftTemplate.disabled, shiftTemplate.workingHours, shiftTemplate.dayOfWeekRules,
//                    shiftTemplate.weekRules, shiftTemplate.maxNrOfSeats, shiftTemplate.defaultOperators)
//            }
//        }

//        db.save(bookingEntity.get())
//    }

//    fun deleteShiftTemplate(bookingEntityId: String, shiftTemplateId: String) {
//        val bookingEntity = getUserEntity(id = bookingEntityId)

//        if (bookingEntity.isEmpty) {
//            throw EntityNotFoundException("Booking entity not found")
//        }

        //var be = bookingEntity.get()
//        bookingEntity.get().shiftTemplates?.removeIf { it.id == shiftTemplateId }

//        db.save(bookingEntity.get())
//    }

    fun getEntityShiftTemplates(entity: UserEntity) {

    }
}
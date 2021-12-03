package com.bytelogics.webookserver.repositories.dao

import com.bytelogics.webookserver.entities.ShiftTemplate
import com.bytelogics.webookserver.entities.UserEntity
import com.bytelogics.webookserver.exceptions.EntityMismatchException
import com.bytelogics.webookserver.exceptions.EntityNotFoundException
import com.bytelogics.webookserver.repositories.IShiftTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class ShiftTemplateImpl (val db: IShiftTemplate,
                         val userEntityImpl: UserEntityImpl) {

    fun findById(id: String) : Optional<ShiftTemplate> = db.findById(id)

    fun getById(id: String): ShiftTemplate {
        val maybeTemplate = findById(id)
        if (maybeTemplate.isEmpty) {
            throw EntityNotFoundException("Template not found")
        }

        return maybeTemplate.get()
    }

    fun create(shiftTemplate: ShiftTemplate): ShiftTemplate {
        // TODO handle duplicates and other exceptions
        return db.save(shiftTemplate)
    }

    fun delete(userId: String, templateId: String) {

        val entity = userEntityImpl.getUserEntity(userId)

        val maybeTemplate = db.findById(templateId)

        if (maybeTemplate.isEmpty) {
            throw EntityNotFoundException("ShiftTemplate does not exist")
        }

        if (maybeTemplate.get().entity != entity.get()) {
            throw EntityMismatchException("Template does not belong to provided entity")
        }

        db.delete(maybeTemplate.get())
    }

    fun update(entityId: String, shiftTemplate: ShiftTemplate): ShiftTemplate = db.save(shiftTemplate)

    fun getEntityShiftTemplates(entityId: String): List<ShiftTemplate> {

        return db.findEntityTemplates(entityId)
    }

    fun create(entityId: String, shiftTemplate: ShiftTemplate): ShiftTemplate {
        val entity = getUserEntity(entityId)

        shiftTemplate.entity = entity.get()
        return db.save(shiftTemplate)
    }

    private fun getUserEntity(entityId: String) : Optional<UserEntity> {
        val maybeUserEntity = userEntityImpl.getUserEntity(entityId)
        if (maybeUserEntity.isEmpty) {
            throw EntityNotFoundException("User entity not found")
        }
        return maybeUserEntity;
    }

}
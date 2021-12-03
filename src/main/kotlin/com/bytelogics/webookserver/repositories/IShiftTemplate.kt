package com.bytelogics.webookserver.repositories

import com.bytelogics.webookserver.entities.ShiftTemplate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface IShiftTemplate: MongoRepository<ShiftTemplate, String> {

    @Query("{ 'entity.id' : ?0}")
    fun findEntityTemplates(userEntityId: String): List<ShiftTemplate>

    @Query("{ 'id' : {\$in: ?0}}")
    fun getTemplatesByIds(ids: List<String>): List<ShiftTemplate>

}
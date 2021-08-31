package com.bytelogics.webookserver.repositories

import com.bytelogics.webookserver.entities.ShiftTemplate
import com.bytelogics.webookserver.entities.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface IShiftTemplate: MongoRepository<ShiftTemplate, String> {

    @Query("{ 'entity.id' : ?0}")
    fun findEntityTemplates(userEntity: UserEntity): List<ShiftTemplate>

}
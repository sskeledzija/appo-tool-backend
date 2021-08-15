package com.bytelogics.webookserver.repositories.dao

import com.bytelogics.webookserver.entities.OrganizationType
import com.bytelogics.webookserver.repositories.IOrganizationType
import org.springframework.stereotype.Service

@Service
class OrganizationTypeImpl (val db: IOrganizationType) {

    fun create(organizationType: OrganizationType): OrganizationType {
        // TODO handle duplicates and other exceptions
        return db.save(organizationType.copy(id = organizationType.id, name = organizationType.name, description = organizationType.description, rate = 1))
    }

    fun findAllOrganizationTypes(): List<OrganizationType> = db.findAll()

    //TODO implement these
//    fun findByName(name: String): Optional<ActivityArea> = db.findByName(name)
//
//    fun findByDescription(description: String): Optional<ActivityArea> = db.findByDescription(description)

}
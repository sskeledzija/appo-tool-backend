package com.bytelogics.webookserver.repositories.dao

import com.bytelogics.webookserver.entities.ActivityField
import com.bytelogics.webookserver.repositories.IActivityArea
import org.springframework.stereotype.Service

@Service
class ActivityAreaImpl (val db: IActivityArea) {



    fun create(activityArea: ActivityField): ActivityField {
        // TODO handle duplicates and other exceptions
        return db.save(activityArea.copy(id = activityArea.id, name = activityArea.name, description = activityArea.description, rate =  1))
    }

    fun findAllActivityAreas(): List<ActivityField> = db.findAll()

    //TODO implement these
//    fun findByName(name: String): Optional<ActivityArea> = db.findByName(name)
//
//    fun findByDescription(description: String): Optional<ActivityArea> = db.findByDescription(description)

}
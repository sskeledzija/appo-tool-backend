package com.bytelogics.webookserver.repositories

import com.bytelogics.webookserver.entities.ActivityField
import org.springframework.data.mongodb.repository.MongoRepository

interface IActivityArea: MongoRepository<ActivityField, String>
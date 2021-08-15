package com.bytelogics.webookserver.repositories

import com.bytelogics.webookserver.entities.OrganizationType
import org.springframework.data.mongodb.repository.MongoRepository

interface IOrganizationType: MongoRepository<OrganizationType, String>
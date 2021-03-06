package com.bytelogics.webookserver.controllers

import com.bytelogics.webookserver.entities.ActivityField
import com.bytelogics.webookserver.entities.ScheduleDay
import com.bytelogics.webookserver.entities.UserEntity
import com.bytelogics.webookserver.entities.OrganizationType
import com.bytelogics.webookserver.entities.templates.AppointmentSlot
import com.bytelogics.webookserver.entities.ShiftTemplate
import com.bytelogics.webookserver.repositories.dao.*
import com.bytelogics.webookserver.services.ShiftTemplateService
import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@CrossOrigin
@RestController("/user-entities")
@RequestMapping("/user-entities")
class UserEntityController(val userEntityImpl: UserEntityImpl,
                           val activityAreaImpl: ActivityAreaImpl,
                           val organizationTypeImpl: OrganizationTypeImpl,
                           val shiftTemplateService: ShiftTemplateService,
                           val appointmentDayImpl: AppointmentDayImpl,
                           val shiftTemplate: ShiftTemplateImpl) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun createBookingEntity(@RequestBody userEntity: UserEntity): ResponseEntity<UserEntity> {
        logger.info("## create booking entity [$userEntity]")
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntityImpl.create(userEntity))
    }

    @GetMapping("/{id}")
    fun getBookingEntity(@PathVariable id: String): ResponseEntity<UserEntity> {
        logger.info("# get booking entity by [$id]")
        return ResponseEntity.of(userEntityImpl.getUserEntity(id))
    }

    @GetMapping("/user/{id}")
    fun getUserEntities(@PathVariable id: String): ResponseEntity<List<UserEntity>> {
        logger.info("# get user's entities. User id [$id]")
        return ResponseEntity.of(userEntityImpl.findAllByUserId(id))
    }

    @CrossOrigin
    @GetMapping("/find")
    fun findBookingEntityByName(@RequestParam key: String): List<UserEntity> {
        logger.info("# find by name or description [$key] ")
        return userEntityImpl.findByNameOrDescription(key)
    }

    /**
     * ACTIVITY AREAS
     * */
    @PostMapping("/activity-areas")
    fun createBookingEntityActivityArea(@RequestBody activityArea: ActivityField): ResponseEntity<ActivityField> {
        logger.info("## create activity area [$activityArea]")
        return ResponseEntity.status(HttpStatus.CREATED).body(activityAreaImpl.create(activityArea))
    }

    @GetMapping("/activity-areas")
    fun getAllActivityAreas(): List<ActivityField> {
        logger.info("# get all activity areas... ")
        return activityAreaImpl.findAllActivityAreas()
    }

    /**
     * ORGANIZATION TYPES
     * */
    @PostMapping("/organization-types")
    fun createBookingEntityOrganizationType(@RequestBody organizationType: OrganizationType): ResponseEntity<OrganizationType> {
        logger.info("## create organization type [$organizationType]")
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationTypeImpl.create(organizationType))
    }

    @GetMapping("/organization-types")
    fun getAllOrganizationalTypes(): List<OrganizationType> {
        logger.info("# get all organizational types...")
        return organizationTypeImpl.findAllOrganizationTypes()
    }

    /**
     * SHIFT TEMPLATES
     * */
    @PostMapping("/{entityId}/shift-templates")
    fun createShiftTemplate(@PathVariable entityId: String, @RequestBody template: ShiftTemplate) {
        logger.info("# Create Shift template for booking entity [$entityId], template data: [$template]")
        shiftTemplate.create(entityId, template)
    }

    @PutMapping("/{entityId}/shift-templates/{templateId}")
    fun updateShiftTemplate(@PathVariable entityId: String, @PathVariable templateId: String, @RequestBody template: ShiftTemplate) {
        logger.info("# Update Shift template for booking entity [$entityId], template data: [$template]")
        shiftTemplate.update(entityId, template)
    }

    @DeleteMapping("/{entityId}/shift-templates/{templateId}")
    fun deleteShiftTemplate(@PathVariable entityId: String, @PathVariable templateId: String) {
        logger.info("# Delete Shift template [$templateId] from booking entity [$entityId]")
        shiftTemplate.delete(entityId, templateId)
    }

    @GetMapping("/{entityId}/shift-templates")
    fun getShiftTemplates(@PathVariable entityId: String): List<ShiftTemplate> {
        logger.info("# Get Shift templates from booking entity [$entityId]")
        return shiftTemplate.getEntityShiftTemplates(entityId)
    }



}
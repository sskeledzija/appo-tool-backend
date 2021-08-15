package com.bytelogics.webookserver.entities.templates

import java.time.Instant
import java.util.*

class OperatorAbsence(val id: UUID = UUID.randomUUID(),
                      var start: Instant,
                      var end: Instant,
                      val operatorId: UUID, // TODO create operator entity - person who does the service
)
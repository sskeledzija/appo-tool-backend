package com.bytelogics.webookserver.entities;

import com.bytelogics.webookserver.entities.templates.Holiday
import java.util.*

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document

@Document
class HolidayTemplate(@Id val id: String = UUID.randomUUID().toString(),
                      val name: String,
                      val holidays: List<Holiday>,
                      val description: String?,
                      val imageUrl: String?)  // custom image which will be shown on calendar
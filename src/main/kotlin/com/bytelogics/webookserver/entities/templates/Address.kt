package com.bytelogics.webookserver.entities.templates

data class Address(//@Id val id: UUID = UUID.randomUUID(),
                   val street: String,
                   val houseNr: String?,
                   val postCode: String,
                   val district: String?,
                   val city: String,
                   val country: String,
                   var consent: Consent?
)
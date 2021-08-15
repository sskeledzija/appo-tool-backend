package com.bytelogics.webookserver.entities.templates

import java.time.Instant

class Consent(val consentType: String,
                val description: String?,
                var crated: Instant,
                var expiry: Instant?) {
}
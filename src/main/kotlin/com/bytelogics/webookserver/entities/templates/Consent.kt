package com.bytelogics.webookserver.entities.templates

import java.time.Instant

class Consent(val gaveConsent: Boolean,
                val description: String?,
                var crated: Instant?,
                var expiry: Instant?) {
}
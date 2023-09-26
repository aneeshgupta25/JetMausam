package com.example.jetmausam.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object UTCtoISTFormatter {
    fun formatUTCtoIST(utcTimeMillis: Long): String {
        // Convert UTC time to Instant
        val utcInstant = Instant.ofEpochMilli(utcTimeMillis)

        // Define the UTC and IST time zones
        val utcZone = ZoneId.of("UTC")
        val istZone = ZoneId.of("Asia/Kolkata")

        // Convert UTC Instant to IST ZonedDateTime
        val istDateTime = utcInstant.atZone(utcZone).withZoneSameInstant(istZone)

        // Format the IST ZonedDateTime as "Sunday, 1 am" using DateTimeFormatter

        return istDateTime.format(
            DateTimeFormatter.ofPattern("EEEE, h a", Locale.ENGLISH)
        )
    }
}
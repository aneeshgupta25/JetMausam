package com.example.jetmausam.utils

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object UTCtoISTFormatter {
    fun getDayTimeFromUST(utcTimeMillis: Long): String {
        // Format the IST ZonedDateTime as "Sunday, 1 am" using DateTimeFormatter
        var istDateTime = getZoneDateTime(utcTimeMillis)
        return istDateTime!!.format(
            DateTimeFormatter.ofPattern("EEEE, hh:mm a", Locale.ENGLISH)
        )
    }

    private fun getZoneDateTime(utcTimeMillis: Long): ZonedDateTime? {
        // Convert UTC time to Instant
        val utcInstant = Instant.ofEpochMilli(utcTimeMillis)

        // Define the UTC and IST time zones
        val utcZone = ZoneId.of("UTC")
        val istZone = ZoneId.of("Asia/Kolkata")

        // Convert UTC Instant to IST ZonedDateTime
        return utcInstant.atZone(utcZone).withZoneSameInstant(istZone)
    }
}
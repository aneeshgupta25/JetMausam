package com.example.jetmausam.utils

import android.content.Context
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


object Utils {

    @Composable
    fun getScreenHeight(): Dp {
        return LocalConfiguration.current.screenHeightDp.dp
    }

    @Composable
    fun getScreenWidth(): Dp {
        return LocalConfiguration.current.screenWidthDp.dp
    }

    fun mapCalendarDayToCustomDay(calendarDay: Int): CustomDayOfWeek {
        return when(calendarDay) {
            Calendar.MONDAY -> CustomDayOfWeek.Monday
            Calendar.TUESDAY -> CustomDayOfWeek.Tuesday
            Calendar.WEDNESDAY -> CustomDayOfWeek.Wednesday
            Calendar.THURSDAY -> CustomDayOfWeek.Thursday
            Calendar.FRIDAY -> CustomDayOfWeek.Friday
            Calendar.SATURDAY -> CustomDayOfWeek.Saturday
            Calendar.SUNDAY -> CustomDayOfWeek.Sunday
            else -> throw IllegalArgumentException("Invalid Day: $calendarDay")
        }

    }

}
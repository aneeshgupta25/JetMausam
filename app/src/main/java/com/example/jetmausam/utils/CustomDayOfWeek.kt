package com.example.jetmausam.utils

enum class CustomDayOfWeek {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday;

    operator fun plus(daysToAdd: Int): CustomDayOfWeek {
        val values = values()
        val currentIndex = this.ordinal
        val newIndex = (currentIndex + daysToAdd) % values.size
        return values[newIndex]
    }
}
package com.example.jetmausam.model.current_day_mausam

data class CurrentDayMausamData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<MausamDailyData>,
    val message: Int
)
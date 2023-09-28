package com.example.jetmausam.model.seven_days_mausam

data class SevenDaysMausamData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<MausamDailySummarizedData>,
    val message: Double
)
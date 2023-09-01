package com.example.jetmausam.model.mausam

data class MausamData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<MausamDailyData>,
    val message: Int
)
package com.example.jetmausam.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetmausam.model.db.Favourites

@Database(entities = [Favourites::class], version = 1, exportSchema = false)
abstract class MausamDatabase: RoomDatabase() {
    abstract fun mausamDao(): MausamDao
}
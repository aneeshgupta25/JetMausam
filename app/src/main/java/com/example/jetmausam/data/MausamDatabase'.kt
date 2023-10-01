package com.example.jetmausam.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetmausam.model.db.Favourites
import com.example.jetmausam.model.db.Unit

@Database(entities = [Favourites::class, Unit::class], version = 3, exportSchema = false)
abstract class MausamDatabase: RoomDatabase() {
    abstract fun mausamDao(): MausamDao
}
package com.example.jetmausam.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetmausam.model.db.Favourites
import com.example.jetmausam.model.db.Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface MausamDao {

    @Query("SELECT * from fav_tbl")
    fun getFavourites(): Flow<List<Favourites>>

    @Query("SELECT * from fav_tbl where city=:city")
    suspend fun getFavById(city: String): Favourites

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavCity(city: Favourites)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavCity(city: Favourites)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAllFavCities()

    @Delete
    suspend fun deleteFavCity(city: Favourites)

    // Units Table
    @Query("SELECT * from unit_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Delete
    suspend fun deleteUnit(unit: Unit)

    @Query("DELETE from unit_tbl")
    suspend fun deleteAllUnits()

}
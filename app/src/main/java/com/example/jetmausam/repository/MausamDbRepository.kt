package com.example.jetmausam.repository

import com.example.jetmausam.data.MausamDao
import com.example.jetmausam.model.db.Favourites
import com.example.jetmausam.model.db.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MausamDbRepository @Inject constructor(private val mausamDao: MausamDao) {

    fun getFavourites(): Flow<List<Favourites>> = mausamDao.getFavourites()
    suspend fun getFavById(city: String): Favourites = mausamDao.getFavById(city = city)
    suspend fun insertFavCity(city: Favourites) = mausamDao.insertFavCity(city = city)
    suspend fun updateFavCity(city: Favourites) = mausamDao.updateFavCity(city = city)
    suspend fun deleteAllFavCities() = mausamDao.deleteAllFavCities()
    suspend fun deleteFavCity(city: Favourites) = mausamDao.deleteFavCity(city = city)

    fun getUnits(): Flow<List<Unit>> = mausamDao.getUnits()
    suspend fun insertUnit(unit: Unit) = mausamDao.insertUnit(unit)
    suspend fun updateUnit(unit: Unit) = mausamDao.updateUnit(unit)
    suspend fun deleteUnit(unit: Unit) = mausamDao.deleteUnit(unit)
    suspend fun deleteAllUnits() = mausamDao.deleteAllUnits()

}
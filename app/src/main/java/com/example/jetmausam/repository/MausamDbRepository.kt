package com.example.jetmausam.repository

import com.example.jetmausam.data.MausamDao
import com.example.jetmausam.model.db.Favourites
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MausamDbRepository @Inject constructor(val mausamDao: MausamDao) {

    fun getFavourites(): Flow<List<Favourites>> = mausamDao.getFavourites()
    suspend fun getFavById(city: String): Favourites = mausamDao.getFavById(city = city)
    suspend fun insertFavCity(city: Favourites) = mausamDao.insertFavCity(city = city)
    suspend fun updateFavCity(city: Favourites) = mausamDao.updateFavCity(city = city)
    suspend fun deleteAllFavCities() = mausamDao.deleteAllFavCities()
    suspend fun deleteFavCity(city: Favourites) = mausamDao.deleteFavCity(city = city)

}
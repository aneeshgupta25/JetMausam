package com.example.jetmausam.screens.fav

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetmausam.model.db.Favourites
import com.example.jetmausam.repository.MausamDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(private val favRepository: MausamDbRepository)
    : ViewModel() {
    private val _favList = MutableStateFlow<List<Favourites>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            favRepository.getFavourites().distinctUntilChanged()
                .collect { listOfFavs ->
                    _favList.value = listOfFavs
                }
        }
    }

    fun insertFavCity(favCity: Favourites) = viewModelScope.launch { favRepository.insertFavCity(favCity) }
    suspend fun getFavById(city: String): String? {
        val favCity = favRepository.getFavById(city)
        return if(favCity != null) "${favCity.city}, ${favCity.country}"
            else null
    }
    fun updateFavCity(favCity: Favourites) = viewModelScope.launch { favRepository.updateFavCity(favCity) }
    fun deleteAllFavCities() = viewModelScope.launch { favRepository.deleteAllFavCities() }
    fun deleteFavCity(favCity: Favourites) = viewModelScope.launch { favRepository.deleteFavCity(favCity) }

}
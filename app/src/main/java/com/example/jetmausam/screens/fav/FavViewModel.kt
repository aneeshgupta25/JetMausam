package com.example.jetmausam.screens.fav

import android.util.Log
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
class FavViewModel @Inject constructor(val favRepository: MausamDbRepository)
    : ViewModel() {
    private val _favList = MutableStateFlow<List<Favourites>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            favRepository.getFavourites().distinctUntilChanged()
                .collect { listOfFavs ->
                    if(listOfFavs.isNullOrEmpty()) {
                        Log.d("TAG", ": empty")
                    } else {
                        _favList.value = listOfFavs
                        Log.d("TAG", ": $listOfFavs")
                    }
                }
        }
    }

    fun insertFavCity(favCity: Favourites) = viewModelScope.launch { favRepository.insertFavCity(favCity) }
    fun getFavById(city: String) = viewModelScope.launch { favRepository.getFavById(city) }
    fun updateFavCity(favCity: Favourites) = viewModelScope.launch { favRepository.updateFavCity(favCity) }
    fun deleteAllFavCities() = viewModelScope.launch { favRepository.deleteAllFavCities() }
    fun deleteFavCity(favCity: Favourites) = viewModelScope.launch { favRepository.deleteFavCity(favCity) }

}
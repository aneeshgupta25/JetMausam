package com.example.jetmausam.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetmausam.data.DataOrException
import com.example.jetmausam.model.current_day_mausam.CurrentDayMausamData
import com.example.jetmausam.model.seven_days_mausam.SevenDaysMausamData
import com.example.jetmausam.repository.MausamRepository
import com.example.jetmausam.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mausamRepository: MausamRepository): ViewModel() {

    private var _currentDayMausamData: MutableState<DataOrException<CurrentDayMausamData, Boolean, Exception>>
    = mutableStateOf(DataOrException(null, true, Exception()))
    private var _sevenDaysMausamData: MutableState<DataOrException<SevenDaysMausamData, Boolean, Exception>>
    = mutableStateOf(DataOrException(null, true, Exception()))
    private var currentCalendarDayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    private var currentCustomDayOfWeek = Utils.mapCalendarDayToCustomDay(currentCalendarDayOfWeek)

    var currentDayMausamData: State<DataOrException<CurrentDayMausamData, Boolean, Exception>>
    = _currentDayMausamData
    var sevenDaysMausamData: State<DataOrException<SevenDaysMausamData, Boolean, Exception>>
    = _sevenDaysMausamData

    fun getMausam(city: String) {
        viewModelScope.launch {
            _currentDayMausamData.value.loading = true
            _sevenDaysMausamData.value.loading = true
            _currentDayMausamData.value = mausamRepository.getCurrentDayMausamData(city)
            _sevenDaysMausamData.value = mausamRepository.getSevenDaysMausamData(city)
            if(_currentDayMausamData.value.data == null || _sevenDaysMausamData.value.data == null) {
                // some log
            } else {
                _currentDayMausamData.value.loading = false
                _sevenDaysMausamData.value.loading = false
            }
        }
    }

    fun getCustomDayWeek(daysToAdd: Int): String {
        return currentCustomDayOfWeek.plus(daysToAdd).name
    }

}
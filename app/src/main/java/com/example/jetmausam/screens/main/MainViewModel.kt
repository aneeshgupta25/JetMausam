package com.example.jetmausam.screens.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetmausam.R
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
    private var _currentCalendarDayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    private var _currentCustomDayOfWeek = Utils.mapCalendarDayToCustomDay(_currentCalendarDayOfWeek)
    // handles drop-down menu visibility
    private var _dropDownDialogVisibility: MutableState<Boolean> = mutableStateOf(false)
    private var _city: MutableState<String> = mutableStateOf("Delhi, IN")
    private var _cityChange: MutableState<Boolean> = mutableStateOf(true)

    var currentDayMausamData: State<DataOrException<CurrentDayMausamData, Boolean, Exception>> = _currentDayMausamData
    var sevenDaysMausamData: State<DataOrException<SevenDaysMausamData, Boolean, Exception>> = _sevenDaysMausamData
    var dropDownDialogVisibility: State<Boolean> = _dropDownDialogVisibility
    var city: State<String> = _city
    var cityChange: State<Boolean> = _cityChange

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
        return _currentCustomDayOfWeek.plus(daysToAdd).name
    }

    fun getCustomImageOfMausam(defaultId: String): Int {
        return when(defaultId) {
            "01d" -> R.drawable.clear_sky_d
            "01n" -> R.drawable.clear_sky_n
            "02d" -> R.drawable.few_clouds_d
            "02n" -> R.drawable.few_clouds_n
            "03d" -> R.drawable.scattered_clouds_d
            "03n" -> R.drawable.shower_rain
            "04d" -> R.drawable.scattered_clouds_d
            "04n" -> R.drawable.shower_rain
            "09d" -> R.drawable.rain_d
            "09n" -> R.drawable.shower_rain
            "10d" -> R.drawable.rain
            "10n" -> R.drawable.shower_rain
            "11d" -> R.drawable.thunderstorm_d
            "11n" -> R.drawable.thunderstorm_n
            "13d" -> R.drawable.snow_d
            "13n" -> R.drawable.snow_n
            "50d" -> R.drawable.mist
            "50n" -> R.drawable.mist
            else -> throw IllegalArgumentException("Invalid Icon: $defaultId")

        }
    }

    fun toggleDropDownDialog() {
        _dropDownDialogVisibility.value = !_dropDownDialogVisibility.value
    }

    fun updateCity(city: String) {
        _cityChange.value = _city.value != city
        _city.value = city
    }

    fun cityChangeReceived() {
        _cityChange.value = false
    }

}
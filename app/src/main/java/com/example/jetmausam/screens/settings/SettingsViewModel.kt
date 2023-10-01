package com.example.jetmausam.screens.settings

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetmausam.model.db.Unit
import com.example.jetmausam.repository.MausamDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val mausamDbRepository: MausamDbRepository)
    : ViewModel() {

    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    private val _unitInCel: MutableState<Boolean> = mutableStateOf(false)
    private val _settingsTextFlag: MutableState<Int> = mutableIntStateOf(0) // 0 -> C, 1 -> F
    private val _changeInSettings: MutableState<Boolean> = mutableStateOf(false)

    val unitList = _unitList.asStateFlow()
    val unitInCel = _unitInCel
    val settingsTextFlag = _settingsTextFlag
    val changeInSettings = _changeInSettings

    init {
        viewModelScope.launch {
            mausamDbRepository.getUnits().distinctUntilChanged()
                .collect { listOfUnit ->
                    if(listOfUnit.isNullOrEmpty()) {
                        mausamDbRepository.insertUnit(Unit("Metric (C)"))
                        unitInCel.value = true
                        _settingsTextFlag.value = 0
                        Log.d("SettingsViewModel", ": empty")
                    } else {
                        val unitListItem = listOfUnit[0].unit.split(" ")[0].lowercase()
                        _unitInCel.value = unitListItem != "imperial"
                        _settingsTextFlag.value = if(unitListItem == "imperial") 1 else 0
                        Log.d("SettingsViewModel", ": $listOfUnit")
                    }
                    _unitList.value = listOfUnit
                }
        }
    }

    fun getUnits(): Flow<List<Unit>> = mausamDbRepository.getUnits()
    fun insertUnit(unit: Unit) = viewModelScope.launch { mausamDbRepository.insertUnit(unit) }
    fun updateUnit(unit: Unit) = viewModelScope.launch { mausamDbRepository.updateUnit(unit) }
    fun deleteUnit(unit: Unit) = viewModelScope.launch { mausamDbRepository.deleteUnit(unit) }
    fun deleteAllUnits() = viewModelScope.launch { mausamDbRepository.deleteAllUnits() }

    fun toggleSettingsText() {
        _settingsTextFlag.value = (1+_settingsTextFlag.value)%2
    }

    fun saveUpdatedSettings() {
        if((_settingsTextFlag.value == 0 && !_unitInCel.value)
            || (_settingsTextFlag.value == 1 && _unitInCel.value)) {
            _changeInSettings.value = true
            Log.d("Hello", "saveUpdatedSettings: yahan aya")
            deleteAllUnits()
            insertUnit(Unit(if(_settingsTextFlag.value == 0) "Metric (C)" else "Imperial (F)"))
        } else {
            _changeInSettings.value = false
        }
    }

    fun settingsUpdated() {
        _changeInSettings.value = false
    }

}
package com.example.bmimvvm

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BMIViewModel: ViewModel() {

    val heightInput: MutableState<String> = mutableStateOf("")
    val weightInput: MutableState<String> = mutableStateOf("")

    private var bmi: MutableState<Float> = mutableStateOf(0.0f)

    fun updateHeightInput(newHeight: String) {
        heightInput.value = newHeight.replace(',', '.')
        calculateBMI()
    }

    fun updateWeightInput(newWeight: String) {
        weightInput.value = newWeight.replace(',', '.')
        calculateBMI()
    }

    fun getBMI(): MutableState<Float> {
        return bmi
    }

    private fun calculateBMI() {
        val heightInCm = heightInput.value.toFloatOrNull() ?: 0.0f
        val weight = weightInput.value.toFloatOrNull() ?: 0.0f
        val heightInMeters = heightInCm / 100
        bmi.value = if (weight > 0 && heightInMeters > 0) weight / (heightInMeters * heightInMeters) else 0.0f
    }
}
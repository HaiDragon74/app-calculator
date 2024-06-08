package com.voduchuy.appcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.voduchuy.appcalculator.model.Calculator
import com.voduchuy.appcalculator.room.CalculateDatabase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HistoryViewModelFactory(private val calculateDatabase: CalculateDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryViewModel(calculateDatabase) as T
    }
}

class HistoryViewModel(private val calculateDatabase: CalculateDatabase) : ViewModel() {
    private val _liveData=MutableLiveData<List<Calculator>>()
    val liveData: LiveData<List<Calculator>> = _liveData

    fun insertCalculate(calculator: Calculator) {
        viewModelScope.launch {
            calculateDatabase.dao().insert(calculator)
        }
    }
    fun realCalculate() {
        calculateDatabase.dao().realCalculator().onEach { list ->
            _liveData.value = list
        }.launchIn(viewModelScope)
    }
}
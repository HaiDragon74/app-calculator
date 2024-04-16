package com.voduchuy.appcalculator.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.voduchuy.appcalculator.Calculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModelFactory(private val calculateDatabase: CalculateDatabase):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryViewModel(calculateDatabase) as T
    }
}

class HistoryViewModel(private val calculateDatabase: CalculateDatabase):ViewModel() {
    private val listCalculate=calculateDatabase.dao().realCalculator()

    fun insertCalculate(calculator: Calculator){
        viewModelScope.launch{
            calculateDatabase.dao().insert(calculator)
        }
    }
    fun realCalculate():LiveData<List<Calculator>>{
        return listCalculate
    }
}
package com.voduchuy.appcalculator.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.voduchuy.appcalculator.Calculator

@Dao
interface Dao {
    @Insert
    suspend fun insert(calculator: Calculator)
    @Query("SELECT * FROM calculator")
    fun realCalculator(): LiveData<List<Calculator>>

}
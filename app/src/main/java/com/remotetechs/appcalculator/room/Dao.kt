package com.voduchuy.appcalculator.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.voduchuy.appcalculator.model.Calculator
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(calculator: Calculator)
    @Query("SELECT * FROM calculator")
    fun realCalculator(): Flow<List<Calculator>>

}
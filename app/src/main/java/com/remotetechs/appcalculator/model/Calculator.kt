package com.voduchuy.appcalculator.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculator")
data class Calculator(
    @PrimaryKey(autoGenerate = true) val itemID: Int,
    val calculation:String?,
    val result: String?,
    val time:String?
)
package com.voduchuy.appcalculator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculator")
data class Calculator(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var calculation:String?=null,
    var result: String?=null,
    var time:String?=null
) {
}
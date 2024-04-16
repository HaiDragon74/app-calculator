package com.voduchuy.appcalculator.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.voduchuy.appcalculator.Calculator
import java.math.MathContext

@Database(entities = [Calculator::class], version = 1, exportSchema = false)
abstract class CalculateDatabase:RoomDatabase() {
    abstract fun dao():Dao
    companion object{
        @Volatile
        private var INSTANCE:CalculateDatabase?=null
        fun getCalculateDatabase(context: Context):CalculateDatabase{
            var instance= INSTANCE
            if (instance!=null){
                return instance
            }
            synchronized(this){
                val newInstance=Room.databaseBuilder(
                    context.applicationContext,
                    CalculateDatabase::class.java,
                    "table_calculate"
                ).build()
                instance=newInstance
                return newInstance
            }
        }
    }
}
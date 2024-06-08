package com.voduchuy.appcalculator.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.voduchuy.appcalculator.model.Calculator

@Database(entities = [Calculator::class], version = 1)
abstract class CalculateDatabase:RoomDatabase() {
    abstract fun dao():Dao
    companion object{
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
                ).allowMainThreadQueries().build()
                instance=newInstance
                return newInstance
            }
        }
    }
}
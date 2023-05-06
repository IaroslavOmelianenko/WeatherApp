package com.github.iaroslavomelianenko.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.iaroslavomelianenko.weatherapp.data.models.City
import com.github.iaroslavomelianenko.weatherapp.utils.DbConstants

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class CityTemperatureDb : RoomDatabase() {

    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var INSTANCE: CityTemperatureDb? = null

        fun getDatabase(context: Context): CityTemperatureDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {  //if instance is not already exists
                return tempInstance
            }
            synchronized(this) {  //everything withing this block is protected from concurrent execution
                //we are using always the same instance
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityTemperatureDb::class.java,
                    DbConstants.TABLE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }
}
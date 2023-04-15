package com.github.iaroslavomelianenko.weatherapp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.github.iaroslavomelianenko.weatherapp.utils.DbConstants

class CityTemperatureDbHelper(context: Context) : SQLiteOpenHelper (
    context,
    DbConstants.DB_NAME,
    null,
    DbConstants.DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL(DbConstants.DB_NAME)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.execSQL(DbConstants.DROP_TABLE)
//        onCreate(db)
    }
}
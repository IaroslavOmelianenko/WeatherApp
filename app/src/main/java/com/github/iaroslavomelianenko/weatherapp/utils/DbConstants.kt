package com.github.iaroslavomelianenko.weatherapp.utils

class DbConstants {
    companion object{
        const val TABLE_NAME = "city_temperature"
        const val _ID = "_id"
        const val CITY = "city"
        const val CITY_TYPE = "city_type"
        const val JANUARY = "jan"
        const val FEBRUARY = "feb"
        const val MARCH = "mar"
        const val APRIL = "apr"
        const val MAY = "may"
        const val JUNE = "jun"
        const val JULY = "jul"
        const val AUGUST = "aug"
        const val SEPTEMBER = "sep"
        const val OCTOBER = "oct"
        const val NOVEMBER = "nov"
        const val DECEMBER = "dec"

        const val DB_NAME = "city_temperature.db"
        const val DB_VERSION = 1

        const val CREATE_TABLE =
            "CREATE TABLE IF NOT EXIST${TABLE_NAME} (" +
                    "${_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${CITY} TEXT," +
                    "${CITY_TYPE} TEXT," +
                    "${JANUARY} TEXT," +
                    "${FEBRUARY} TEXT," +
                    "${MARCH} TEXT," +
                    "${APRIL} TEXT," +
                    "${MAY} TEXT," +
                    "${JUNE} TEXT," +
                    "${JULY} TEXT," +
                    "${AUGUST} TEXT," +
                    "${SEPTEMBER} TEXT," +
                    "${OCTOBER} TEXT," +
                    "${NOVEMBER} TEXT," +
                    "${DECEMBER} TEXT,"

        const val DROP_TABLE = "DROP TABLE IF EXISTS ${TABLE_NAME}"
    }
}
package com.github.iaroslavomelianenko.weatherapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.iaroslavomelianenko.weatherapp.utils.DbConstants
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = DbConstants.TABLE_NAME)
data class City(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val city: String,
    val cityType: String,
    val jan: Int,
    val feb: Int,
    val mar: Int,
    val apr: Int,
    val may: Int,
    val jun: Int,
    val jul: Int,
    val aug: Int,
    val sep: Int,
    val oct: Int,
    val nov: Int,
    val dec: Int
): Parcelable

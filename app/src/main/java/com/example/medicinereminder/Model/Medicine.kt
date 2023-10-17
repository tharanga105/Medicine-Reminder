package com.example.medicinereminder.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Medicine")
class Medicine (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var medicineName: String,
    var dose:  String,
    var date: String,
    var priority: String,



): Parcelable
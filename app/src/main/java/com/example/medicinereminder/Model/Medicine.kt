package com.example.medicinereminder.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Medicine")

class Medicine (

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var meicineName: String,
    var dose: String,
    var discription: String,
    var date: String,
    var priority: String,






        )
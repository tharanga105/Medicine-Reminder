package com.example.medicinereminder.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.medicinereminder.Dao.MedicineDao
import com.example.medicinereminder.Model.Medicine

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class MedicineDatabase: RoomDatabase() {

    abstract fun myMedicineDao(): MedicineDao

    companion object{
        @Volatile
        var INSTANCE: MedicineDatabase?=null
        fun getDatabaseInstance(context: Context): MedicineDatabase{

            val tempInstance= INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstance=Room.
                databaseBuilder(context,MedicineDatabase::class.java,"Medicine").allowMainThreadQueries() .build()
                INSTANCE = roomDatabaseInstance
                return return roomDatabaseInstance
            }
        }
    }
}
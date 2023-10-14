package com.example.medicinereminder.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.medicinereminder.Model.Medicine


@Dao
interface MedicineDao {
    @Query("SELECT * FROM Medicine")
    fun gatMedicine(): LiveData<List<Medicine>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun  insertMedicine(medicine: Medicine)

    @Query( "DELETE FROM MEDICINE WHERE id=:id")
    fun deleteMedicine(id:Int)
    @Update
    fun  updateMedicine(medicine: Medicine)


}
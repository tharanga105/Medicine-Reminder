package com.example.medicinereminder.Repository

import androidx.lifecycle.LiveData
import com.example.medicinereminder.Dao.MedicineDao
import com.example.medicinereminder.Model.Medicine

class  MedicineRepository(val dao: MedicineDao) {

    fun getAllMedicine():LiveData<List<Medicine>> {
        return dao.gatMedicine()
    }
    fun insertMedicine (medicine: Medicine){
         dao.insertMedicine(medicine)
    }

    fun deleteMedicine(id:Int){
        dao.deleteMedicine(id)
    }

    fun updateMedicine(medicine: Medicine){
        dao.updateMedicine(medicine)
    }
}
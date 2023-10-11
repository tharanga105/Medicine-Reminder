package com.example.medicinereminder.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.medicinereminder.Database.MedicineDatabase
import com.example.medicinereminder.Model.Medicine
import com.example.medicinereminder.Repository.MedicineRepository

class  MedicineViewModel(application:Application):AndroidViewModel(application) {

    val repository:MedicineRepository

    init {
        val dao=MedicineDatabase.getDatabaseInstance(application).myMedicineDao()
        repository=MedicineRepository(dao)
    }

    fun addMedicine(medicine: Medicine){
        repository.insertMedicine(medicine)
    }

    fun getMedicine():LiveData<List<Medicine>> = repository. getAllMedicine()

    fun deleteMedicine(id:Int){
        repository.deleteMedicine(id)
    }
    fun updateMedicine(medicine: Medicine){
        repository.updateMedicine(medicine)
    }
}
package com.example.medicinereminder.ui.Fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medicinereminder.Model.Medicine
import com.example.medicinereminder.R
import com.example.medicinereminder.ViewModel.MedicineViewModel
import com.example.medicinereminder.databinding.FragmentCreateMedicineBinding
import java.util.*


class CreateMedicineFragment : Fragment() {

    lateinit var binding: FragmentCreateMedicineBinding
    var priority: String = "1"
    val viewModel : MedicineViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding = FragmentCreateMedicineBinding.inflate(layoutInflater, container, false)







        binding.pBlue.setImageResource(R.drawable.ic_baseline_done_24)

        binding.pBlue.setOnClickListener {
            priority = "1"
            binding.pBlue.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)


        }

        binding.pGreen.setOnClickListener {
            priority = "2"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pBlue.setImageResource(0)

        }

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pBlue.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)

        }

        binding.pYellow.setOnClickListener {
            priority = "4"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pBlue.setImageResource(0)
            binding.pGreen.setImageResource(0)

        }

        binding.btnSaveMedicine.setOnClickListener {

            createMedicine(it)
        }


        return binding.root
    }


    private fun createMedicine(it: View?) {
        val medicineName = binding.edtMedicineName.text.toString()
        val dose = binding.editDose.text.toString()
        val discription = binding.edtDescription.text.toString()

        val d = Date()
        val medicineDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data = Medicine(
            null,
            meicineName = medicineName,
            dose = dose,
            discription = discription,
            date = medicineDate.toString(),
            priority


        )
        viewModel.addMedicine(data)
        Toast.makeText(requireContext(), "Create medicine success", Toast.LENGTH_SHORT).show()


    }


}
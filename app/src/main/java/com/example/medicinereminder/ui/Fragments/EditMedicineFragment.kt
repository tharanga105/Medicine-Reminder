package com.example.medicinereminder.ui.Fragments

import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.medicinereminder.Model.Medicine
import com.example.medicinereminder.R
import com.example.medicinereminder.ViewModel.MedicineViewModel
import com.example.medicinereminder.databinding.FragmentEditMedicineBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.w3c.dom.Text
import java.nio.file.Files.delete
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.time.Duration


class EditMedicineFragment : Fragment() {

    val oldMedicine by navArgs<EditMedicineFragmentArgs>()
    lateinit var binding: FragmentEditMedicineBinding
    var priority: String = "1"

    val viewModel: MedicineViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentEditMedicineBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)


        binding.edtMedicineName.setText(oldMedicine.data.medicineName)
        binding.editDose.setText(oldMedicine.data.dose)

        when (oldMedicine.data.priority) {
            "1" -> {
                priority = "1"
                binding.pBlue.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)

            }
            "2" -> {
                priority = "2"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.pBlue.setImageResource(0)

            }
            "3" -> {
                priority = "3"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pBlue.setImageResource(0)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)

            }
            "4" -> {
                priority = "4"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pBlue.setImageResource(0)
                binding.pGreen.setImageResource(0)

            }


        }

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


        binding.btnEditSaveMedicine.setOnClickListener {
            updateMedicine(it)
        }



        return binding.root
    }

    private fun updateMedicine(it: View?) {

        val medicineName = binding.edtMedicineName.text.toString()
        val dose = binding.editDose.text.toString()
        val discription = binding.edtDescription.text.toString()

        val d = Date()
        val medicineDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data = Medicine(
            oldMedicine.data.id,
            medicineName = medicineName,
            dose = dose,
            date = medicineDate.toString(),
            priority


        )
        viewModel.updateMedicine(data)
        Toast.makeText(requireContext(), " Medicine updated successfully", Toast.LENGTH_SHORT)
            .show()

        Navigation.findNavController(it!!)
            .navigate(R.id.action_editMedicineFragment_to_homeFragment)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            val bottomhsheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomhsheet.setContentView(R.layout.dialog_delete)

            val textviewYes = bottomhsheet.findViewById<TextView>(R.id.daalog_yes)
            val textviewNo = bottomhsheet.findViewById<TextView>(R.id.dialog_no)

            textviewYes?.setOnClickListener {
                viewModel.deleteMedicine(oldMedicine.data.id!!)
                bottomhsheet.dismiss()

            }

            textviewNo?.setOnClickListener {
                bottomhsheet.dismiss()
            }

            bottomhsheet.show()
        }


        return super.onOptionsItemSelected(item)
    }
}










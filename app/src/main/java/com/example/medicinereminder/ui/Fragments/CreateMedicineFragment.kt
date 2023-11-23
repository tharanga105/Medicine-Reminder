package com.example.medicinereminder.ui.Fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.medicinereminder.AlarmReceiver
import com.example.medicinereminder.Model.Medicine
import com.example.medicinereminder.R
import com.example.medicinereminder.ViewModel.MedicineViewModel
import com.example.medicinereminder.databinding.FragmentCreateMedicineBinding
import java.text.SimpleDateFormat
import java.util.*


class CreateMedicineFragment : Fragment() {

    lateinit var binding: FragmentCreateMedicineBinding
    var priority: String = "1"
    val viewModel : MedicineViewModel by viewModels()

    var triggerAtMillis: Long = 0




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding = FragmentCreateMedicineBinding.inflate(layoutInflater, container, false)







        binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)

        binding.pRed.setOnClickListener {
            priority = "1"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pBlue.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)


        }

        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
            binding.pBlue.setImageResource(0)

        }

        binding.pGreen.setOnClickListener {
            priority = "3"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pBlue.setImageResource(0)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)

        }

        binding.pBlue.setOnClickListener {
            priority = "4"
            binding.pBlue.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
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


        val d = Date()
        val medicineDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data = Medicine(
            null,
            medicineName = medicineName,
            dose = dose,
            date = medicineDate.toString(),
            priority


        )
        viewModel.addMedicine(data)
        Toast.makeText(requireContext(), "Create medicine success", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createMedicineFragment_to_homeFragment)


    }
    private fun setRepeatingAlarm() {
        val intent = Intent(requireActivity(), AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            requireActivity(),
            0,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Set the repeating alarm with a 1-minute interval
        val intervalMillis = java.lang.Long.valueOf(binding.etInterval.text.toString());
        // 1 minute in milliseconds
        val alarmManager: AlarmManager =
            requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            intervalMillis.toLong(),
            pendingIntent
        )
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, selectedHour, selectedMinute ->
                // Handle the selected start time
                val selectedCalendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, selectedHour)
                    set(Calendar.MINUTE, selectedMinute)
                    set(Calendar.SECOND, 59)
                    set(Calendar.MILLISECOND, 0)
                }

                triggerAtMillis = selectedCalendar.timeInMillis

                // Format the selected time and set it to the TextView
                val formattedTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(selectedCalendar.time)
                binding.selectTime.text = formattedTime

                // You can use selectedStartTime as needed
            },
            hour,
            minute,
            false // set to true if you want 24-hour format
        )

        timePickerDialog.show()
    }

}



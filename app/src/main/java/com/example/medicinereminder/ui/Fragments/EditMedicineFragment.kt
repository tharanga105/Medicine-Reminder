package com.example.medicinereminder.ui.Fragments

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.medicinereminder.R
import com.example.medicinereminder.databinding.FragmentEditMedicineBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import java.time.Duration


class EditMedicineFragment : Fragment() {


    lateinit var binding: FragmentEditMedicineBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)

        binding = FragmentEditMedicineBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            val bottomhsheet: BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomhsheet.setContentView(R.layout.dialog_delete)
            bottomhsheet.show()
        }


        return super.onOptionsItemSelected(item)
    }
}









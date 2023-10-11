package com.example.medicinereminder.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicinereminder.R
import com.example.medicinereminder.ViewModel.MedicineViewModel
import com.example.medicinereminder.databinding.FragmentHomeBinding
import com.example.medicinereminder.ui.Adapter.MedicineAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel : MedicineViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)

        viewModel.getMedicine().observe(viewLifecycleOwner, { medicineList ->

            binding.rcvAllMedicine.layoutManager = LinearLayoutManager(requireContext())
            binding.rcvAllMedicine.adapter=MedicineAdapter(requireContext(),medicineList)
        })


        binding.btnAddMedicine.setOnClickListener {

             Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createMedicineFragment)
         }
        return binding.root
    }


}
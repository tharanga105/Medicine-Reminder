package com.example.medicinereminder.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicinereminder.Model.Medicine
import com.example.medicinereminder.R
import com.example.medicinereminder.ViewModel.MedicineViewModel
import com.example.medicinereminder.databinding.FragmentHomeBinding
import com.example.medicinereminder.ui.Adapter.MedicineAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: MedicineViewModel by viewModels()
    var oldMyMedicine= arrayListOf<Medicine>()
    lateinit var adapter: MedicineAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        viewModel.getMedicine().observe(viewLifecycleOwner, { medicineList ->

            binding.rcvAllMedicine.layoutManager = LinearLayoutManager(requireContext())
            binding.rcvAllMedicine.adapter = MedicineAdapter(requireContext(), medicineList)
        })

        binding.filterRed.setOnClickListener{

            viewModel.getRedMedicine().observe(viewLifecycleOwner, { medicineList ->

                binding.rcvAllMedicine.layoutManager = LinearLayoutManager(requireContext())
                binding.rcvAllMedicine.adapter = MedicineAdapter(requireContext(), medicineList)
            })

        }

        binding.allMedicine.setOnClickListener {
            viewModel.getMedicine().observe(viewLifecycleOwner, { medicineList ->
                oldMyMedicine = medicineList as ArrayList<Medicine>

                binding.rcvAllMedicine.layoutManager = LinearLayoutManager(requireContext())
                binding.rcvAllMedicine.adapter = MedicineAdapter(requireContext(), medicineList)
            })

        }
        binding.filterYellow.setOnClickListener {

            viewModel.getYellowMedicine().observe(viewLifecycleOwner, { medicineList ->
                oldMyMedicine = medicineList as ArrayList<Medicine>
                binding.rcvAllMedicine.layoutManager = LinearLayoutManager(requireContext())
                binding.rcvAllMedicine.adapter = MedicineAdapter(requireContext(), medicineList)
            })
        }
        binding.filterGreen.setOnClickListener {

            viewModel.getGreenMedicine().observe(viewLifecycleOwner, { medicineList ->
                oldMyMedicine = medicineList as ArrayList<Medicine>
                binding.rcvAllMedicine.layoutManager = LinearLayoutManager(requireContext())
                binding.rcvAllMedicine.adapter = MedicineAdapter(requireContext(), medicineList)
            })
        }
        binding.filterBlue.setOnClickListener {

            viewModel.getBlueMedicine().observe(viewLifecycleOwner, { medicineList ->
                oldMyMedicine = medicineList as ArrayList<Medicine>
                binding.rcvAllMedicine.layoutManager = LinearLayoutManager(requireContext())
                binding.rcvAllMedicine.adapter = MedicineAdapter(requireContext(), medicineList)
            })
        }



        binding.btnAddMedicine.setOnClickListener {

            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createMedicineFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)

        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Medicine Name Here...."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                MedicineFiltering(p0)
                return true
            }
        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun MedicineFiltering(p0: String?) {
        val newFilteredList = arrayListOf<Medicine>()

    }
}
    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("Service Start")
        val startTime = Calendar.getInstance()
        startTime.set(2023, Calendar.OCTOBER, 14, 22,8 ,0 ) // Replace with your desired start date and time


        val currentTime = Calendar.getInstance()

        // Create a scheduled executor service
        val scheduler: ScheduledExecutorService = Executors.newScheduledThreadPool(1)

        // Schedule a recurring task
        scheduler.scheduleAtFixedRate({
            // Place your task logic here
            println("Recursive task executed at ${Date()}")
        }, 1, 1, TimeUnit.MINUTES)
    }

}*/
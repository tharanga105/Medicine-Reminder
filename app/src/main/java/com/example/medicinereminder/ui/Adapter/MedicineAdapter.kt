package com.example.medicinereminder.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminder.Model.Medicine
import com.example.medicinereminder.R
import com.example.medicinereminder.databinding.ItemDescriptionBinding
import com.example.medicinereminder.ui.Fragments.HomeFragmentDirections


class MedicineAdapter(val requireContext: Context, val medicineList: List<Medicine>) :
    RecyclerView.Adapter<MedicineAdapter.medicineViewHolder>() {
    class medicineViewHolder(val binding: ItemDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): medicineViewHolder {
        return medicineViewHolder(
            ItemDescriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: medicineViewHolder, position: Int) {
        val data = medicineList[position]
        holder.binding.medicineName.text = data.medicineName
        holder.binding.medicineDose.text= data.dose
        holder.binding.medicineDate.text= data.date

        when (data.priority) {
            "1" -> {
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
            "2" ->{holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)

            }
            "3" ->{holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)

            }
            "4" -> {holder.binding.viewPriority.setBackgroundResource(R.drawable.blue_dot)

            }



        }
        holder.binding.root.setOnClickListener{

            val action = HomeFragmentDirections.actionHomeFragmentToEditMedicineFragment(data)
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount() = medicineList.size
}

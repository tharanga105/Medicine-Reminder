package com.example.medicinereminder.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminder.Model.Medicine
import com.example.medicinereminder.R
import com.example.medicinereminder.databinding.ItemDescriptionBinding

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
        holder.binding.medicineName.text = data.meicineName
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

    }

    override fun getItemCount() = medicineList.size
}

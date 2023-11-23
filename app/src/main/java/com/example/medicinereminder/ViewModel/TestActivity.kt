package com.example.medicinereminder.ViewModel

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.medicinereminder.AlarmReceiver
import com.example.medicinereminder.R
import com.example.medicinereminder.databinding.ActivityTestBinding
import java.util.Calendar

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)

        binding.tvTime.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                // Handle the selected start time
                val selectedCalendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, selectedHour)
                    set(Calendar.MINUTE, selectedMinute)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }

                setRepeatingAlarm(selectedCalendar.timeInMillis)


                // You can use selectedStartTime as needed
            },
            hour,
            minute,
            false // set to true if you want 24-hour format
        )

        timePickerDialog.show()
    }

    private fun setRepeatingAlarm(triggerAtMillis: Long) {
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Set the repeating alarm with a 1-minute interval
        val intervalMillis = 1 * 60 * 1000 // 1 minute in milliseconds
        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            intervalMillis.toLong(),
            pendingIntent
        )
    }

}
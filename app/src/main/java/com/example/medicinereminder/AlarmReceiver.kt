package com.example.medicinereminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("TEST ALARM", "Trigger")
        Toast.makeText(context, "Trigger Alarm", Toast.LENGTH_SHORT).show()
    }
}
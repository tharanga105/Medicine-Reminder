package com.example.medicinereminder

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.getSystemService
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.medicinereminder.databinding.ActivityMainBinding

class  MainActivity : AppCompatActivity() {

    lateinit var pendingIntent: PendingIntent
    lateinit var alarmManager: AlarmManager

    private lateinit var binding : ActivityMainBinding

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)








        navController=findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(navController)






    }





    override fun onNavigateUp(): Boolean {
        return navController.navigateUp()|| super.onNavigateUp()

    }
}
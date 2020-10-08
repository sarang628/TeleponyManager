package com.example.teleponymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import com.example.teleponymanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(activityMainBinding.root)

        val telephonyManager : TelephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //Log.e("sarang", telephonyManager.imei)
        }

        activityMainBinding.callState.text = telephonyManager.callState.toString()

        activityMainBinding.btnRefresh.setOnClickListener {
            activityMainBinding.callState.text = telephonyManager.callState.toString()
        }

    }
}
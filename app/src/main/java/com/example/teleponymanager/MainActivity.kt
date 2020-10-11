package com.example.teleponymanager

import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import com.example.srteleponymanager.SrTelephonyManager
import com.example.srteleponymanager.SrTelephonyManagerImpl
import com.example.teleponymanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var telephonyManager: TelephonyManager
    lateinit var srTelephonyManager: SrTelephonyManager
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        srTelephonyManager = SrTelephonyManagerImpl(this)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(activityMainBinding.root)

        telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        activityMainBinding.callState.text = srTelephonyManager.getCallState()
        activityMainBinding.imei.text = srTelephonyManager.getImei()
        activityMainBinding.line1Number.text = srTelephonyManager.getLine1Number()
        activityMainBinding.simOperator.text = telephonyManager.simOperator
        activityMainBinding.tvIsWorldPhone.text = "${telephonyManager.isWorldPhone}"
    }

}
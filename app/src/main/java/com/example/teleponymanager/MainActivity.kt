package com.example.teleponymanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.srteleponymanager.SrTelephonyManager
import com.example.teleponymanager.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var srTelephonyManager: SrTelephonyManager

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(activityMainBinding.root)

        //request permission
        //srTelephonyManager.requestImeiPermission(this)

        activityMainBinding.callState.text = srTelephonyManager.getCallState()
        activityMainBinding.imei.text = srTelephonyManager.getImei()
        activityMainBinding.line1Number.text = srTelephonyManager.getLine1Number()
    }
}
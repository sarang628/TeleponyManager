package com.example.teleponymanager

import android.Manifest
import android.R
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.teleponymanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var telephonyManager: TelephonyManager
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(activityMainBinding.root)

        telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        activityMainBinding.callState.text = getCallState()
        activityMainBinding.imei.text = getImei()
        activityMainBinding.line1Number.text = getLine1Number()
    }

    private fun getLine1Number(): String {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_NUMBERS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activityMainBinding.btnPermission.setOnClickListener {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.READ_SMS,
                        Manifest.permission.READ_PHONE_NUMBERS,
                        Manifest.permission.READ_PHONE_STATE
                    ), 0x01
                )
            }
            return "permission denied"
        }
        return "${telephonyManager.line1Number}"
    }

    fun getCallState(): String {
        return telephonyManager.callState.toString()
    }

    fun getImei(): String {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return "permission denied"

            requestPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE), 0x01)
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return "${telephonyManager.imei}"
        } else {
            return "${telephonyManager.deviceId}"
        }
    }
}
package com.example.srteleponymanager

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class SrTelephonyManagerImpl constructor(val context: Context) : SrTelephonyManager {

    private val telephonyManager: TelephonyManager =
        context.getSystemService(AppCompatActivity.TELEPHONY_SERVICE) as TelephonyManager

    override fun getLine1Number(): String {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_NUMBERS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return "permission denied"
        }
        return "${telephonyManager.line1Number}"
    }

    override fun getCallState(): String {
        return telephonyManager.callState.toString()
    }

    override fun getImei(): String {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return "permission denied"
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return telephonyManager.imei
        } else {
            return telephonyManager.deviceId
        }
    }

    override fun requestLineNumberPermission(activity: AppCompatActivity) {
        activity.requestPermissions(
            arrayOf(
                Manifest.permission.READ_SMS,
                Manifest.permission.READ_PHONE_NUMBERS,
                Manifest.permission.READ_PHONE_STATE
            ), 0x01
        )
    }

    override fun requestImeiPermission(activity: AppCompatActivity) {
        activity.requestPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE), 0x01)
    }
}
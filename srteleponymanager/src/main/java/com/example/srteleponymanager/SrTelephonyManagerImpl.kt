package com.example.srteleponymanager

import android.content.Context
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity

class SrTelephonyManagerImpl constructor(val context: Context) : SrTelephonyManager {

    private val teleponyManager: TelephonyManager
            = context.getSystemService(AppCompatActivity.TELEPHONY_SERVICE) as TelephonyManager

    override fun getLine1Number(): String {
        TODO("Not yet implemented")
    }

    override fun getCallState(): String {
        TODO("Not yet implemented")
    }

    override fun getImei(): String {
        TODO("Not yet implemented")
    }
}
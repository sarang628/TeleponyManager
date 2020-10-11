package com.example.teleponymanager

import android.app.Application
import com.example.srteleponymanager.SrTeleponyManagerModule

class MyApplication : Application() {
    val appComponent = DaggerAppComponent.builder()
        .srTeleponyManagerModule(SrTeleponyManagerModule(this))
        .build()
}
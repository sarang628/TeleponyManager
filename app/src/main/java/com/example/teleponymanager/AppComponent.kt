package com.example.teleponymanager

import com.example.srteleponymanager.SrTeleponyManagerModule
import dagger.Component

@Component(modules = [SrTeleponyManagerModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
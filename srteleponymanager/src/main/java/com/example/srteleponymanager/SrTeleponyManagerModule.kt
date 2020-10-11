package com.example.srteleponymanager

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class SrTeleponyManagerModule constructor(val context: Context) {

    @Provides
    fun provideSrTeleponyManager(): SrTelephonyManager {
        return SrTelephonyManagerImpl(context)
    }
}
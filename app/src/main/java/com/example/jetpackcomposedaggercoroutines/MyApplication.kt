package com.example.jetpackcomposedaggercoroutines

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
     override fun onCreate() {
         super.onCreate()
         Log.d(TAG,"Coming inside onCreate")
     }


     companion object{
         const val TAG="MyApplication"
     }
}
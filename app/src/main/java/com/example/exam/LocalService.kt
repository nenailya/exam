package com.example.exam

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class LocalService : Service() {
val binder = LocalBinder()
    override fun onBind(intent: Intent): IBinder {
return binder
    }
    fun nextRandomInt(): Int {
        return((Math.random() * 2 * Int.MAX_VALUE - Int.MAX_VALUE).toInt())
    }
    inner class LocalBinder : Binder(){
        fun getServise(): LocalService = this@LocalService
    }
}
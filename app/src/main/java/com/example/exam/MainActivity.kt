package com.example.exam

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var mService: LocalService
    private var mBound: Boolean = false

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as LocalService.LocalBinder
            mService = binder.getServise()
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener { onButtonClick() }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, LocalService::class.java)
            .also {
                bindService(it, connection, Context.BIND_AUTO_CREATE)
            }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }

    fun onButtonClick() {
        if (mBound) {
            val num: Int = mService.nextRandomInt()
            Toast.makeText(this, "number: $num", Toast.LENGTH_SHORT).show()
        }
    }
}
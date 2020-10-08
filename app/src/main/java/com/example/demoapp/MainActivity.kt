package com.example.demoapp

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.ScheduledExecutorService

class MainActivity : AppCompatActivity() {
    private lateinit var textMessage: TextView
    companion object {
        val TAG : String = "MainActivity";
    }
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                textMessage.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                textMessage.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        //rx java test code
        rxJavaFlatMap()
    }

    fun rxJavaFlatMap() {
        val obserable : Observable<Int> = Observable.fromArray(1, 3, 5, 7).
            flatMap {
                Observable.fromArray(it, it+1)
            }

        obserable.subscribe({
                Log.d(TAG, "value: " + it)
            })
    }

    fun rxJavaSchedule() {
        val observable : Observable<Int> = Observable.fromArray(1, 2, 3, 4, 5).
                map {
                    it * it
                }.subscribeOn(Schedulers.io())
    }
}

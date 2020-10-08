package com.example.demoapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {
//    private static Context context;
    val context = ApplicationProvider.getApplicationContext<Context>()
    @Test
    fun onCreate() {


    }

    @Test
    fun rxJavaFlatMap() {
    }

    @Test
    fun rxJavaSchedule() {
    }
}
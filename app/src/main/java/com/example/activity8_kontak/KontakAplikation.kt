package com.example.activity8_kontak

import android.app.Application
import com.example.activity8_kontak.repository.AppContainer
import com.example.activity8_kontak.repository.KontakContainer

class KontakAplikation : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = KontakContainer()
    }
}
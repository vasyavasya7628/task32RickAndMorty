package com.example.task32rickandmorty

import android.app.Application
import com.example.task32rickandmorty.data.HeroesApi
import timber.log.Timber

class App : Application() {
    val api: HeroesApi = HeroesApi.create()
    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}
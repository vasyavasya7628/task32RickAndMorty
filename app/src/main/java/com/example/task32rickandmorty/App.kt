package com.example.task32rickandmorty

import android.app.Application
import com.example.task32rickandmorty.data.HeroesApi
import timber.log.Timber

class App() : Application() {

    companion object{
        private lateinit var api: HeroesApi
    }
    override fun onCreate() {
        api = HeroesApi.create()
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }

}
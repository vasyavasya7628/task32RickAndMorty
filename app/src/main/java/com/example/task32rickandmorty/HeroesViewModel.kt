package com.example.task32rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task32rickandmorty.data.HeroesLocal
import com.example.task32rickandmorty.data.HeroesNetwork
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class HeroesViewModel : ViewModel() {
    private val api: HeroesApi = HeroesApi.create()
    private var _weather: MutableLiveData<List<HeroesLocal>> =
        MutableLiveData<List<HeroesLocal>>()
    val weatherExport: LiveData<List<HeroesLocal>> get() = _weather

    private fun getDataNetwork(){
        api.heroesPage(
            1
        ).enqueue(object : retrofit2.Callback<HeroesNetwork>
        {
            override fun onResponse(call: Call<HeroesNetwork>, response: Response<HeroesNetwork>) {
                val heroes:HeroesNetwork = response.body() as HeroesNetwork
                val heroesDomain: List<HeroesLocal> =
            }

            override fun onFailure(call: Call<HeroesNetwork>, t: Throwable) {
                Timber.tag("Net_err").d("Ошибка данных")
            }

        })
    }
}
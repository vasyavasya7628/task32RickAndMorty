package com.example.task32rickandmorty.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task32rickandmorty.data.HeroesApi
import com.example.task32rickandmorty.data.HeroesLocal
import com.example.task32rickandmorty.data.HeroesNetwork
import com.example.task32rickandmorty.data.toDomain
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class HeroesViewModel : ViewModel() {
    private val api: HeroesApi = HeroesApi.create()
    private var _heroes: MutableLiveData<List<HeroesLocal>> =
        MutableLiveData<List<HeroesLocal>>()
    val heroesExport: LiveData<List<HeroesLocal>> get() = _heroes
    init {
        getDataNetwork()
    }
    private fun getDataNetwork() {
        api.getCharacter(
        ).enqueue(object : retrofit2.Callback<HeroesNetwork> {

            override fun onResponse(
                    call: Call<HeroesNetwork>,
                    response: Response<HeroesNetwork>
            ) {
                val heroesNetwork: HeroesNetwork = response.body() as HeroesNetwork
                val heroesDomain: List<HeroesLocal> = heroesNetwork.results.map {
                    it.toDomain()
                }
                _heroes.value = heroesDomain
            }

            override fun onFailure(call: Call<HeroesNetwork>, t: Throwable) {
                Timber.tag("Net_err").d("Ошибка данных")
            }

        })
    }
}
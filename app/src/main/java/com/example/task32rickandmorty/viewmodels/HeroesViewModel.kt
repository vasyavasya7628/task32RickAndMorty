package com.example.task32rickandmorty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task32rickandmorty.App
import com.example.task32rickandmorty.data.HeroesNetwork
import com.example.task32rickandmorty.data.toUI
import com.example.task32rickandmorty.ui.HeroesUI
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class HeroesViewModel : ViewModel() {
    private var _heroes: MutableLiveData<List<HeroesUI>> =
        MutableLiveData<List<HeroesUI>>()
    val heroesExport: LiveData<List<HeroesUI>> get() = _heroes

    init {
        getDataNetwork()
    }

    private fun getDataNetwork() {
        App().api.getCharacter(
        ).enqueue(object : retrofit2.Callback<HeroesNetwork> {

            override fun onResponse(
                call: Call<HeroesNetwork>,
                response: Response<HeroesNetwork>,
            ) {
                val heroesNetwork: HeroesNetwork = response.body() as HeroesNetwork
                val heroesDomain: List<HeroesUI> = heroesNetwork.results.map { result ->
                    result.toUI()
                }
                _heroes.value = heroesDomain
            }

            override fun onFailure(call: Call<HeroesNetwork>, t: Throwable) {
                Timber.e(t.toString())
            }

        })
    }
}
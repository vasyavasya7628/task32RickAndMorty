package com.example.task32rickandmorty.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task32rickandmorty.data.HeroesApi
import com.example.task32rickandmorty.data.HeroesNetwork
import com.example.task32rickandmorty.data.PagesLocal
import com.example.task32rickandmorty.data.toDomain
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class HeroesViewModel : ViewModel() {
    private val api: HeroesApi = HeroesApi.create()
    private var _heroes: MutableLiveData<List<HeroesUi>> =
        MutableLiveData<List<HeroesUi>>()
    val heroesExport: LiveData<List<HeroesUi>> get() = _heroes
    private var currentPage = 1

    init {
        getDataNetwork(currentPage)
    }

    fun loadNextPage() {
        getDataNetwork(currentPage)
    }

    private fun getDataNetwork(page: Int) {
        api.getCharacter(
            page
        ).enqueue(object : retrofit2.Callback<HeroesNetwork> {

            override fun onResponse(
                call: Call<HeroesNetwork>,
                response: Response<HeroesNetwork>
            ) {
                val heroesNetwork: HeroesNetwork = response.body() as HeroesNetwork
                val heroesDomain: PagesLocal = heroesNetwork.toDomain()
                val newHeroesList: MutableList<HeroesUi> = heroesDomain.heroesList.map {
                    HeroesUi.Data(it)
                }.toMutableList()
                if (heroesDomain.pages > currentPage) {
                    currentPage++
                    Timber.tag("Page").d(currentPage.toString())
                    newHeroesList.add(HeroesUi.NextPage)
                }

                val oldHeroesList: MutableList<HeroesUi> =
                    _heroes.value?.toMutableList() ?: mutableListOf()
                oldHeroesList.addAll(newHeroesList)

                if (currentPage > page) {
                    oldHeroesList.remove(HeroesUi.NextPage)
                    _heroes.value = newHeroesList
                }
            }

            override fun onFailure(call: Call<HeroesNetwork>, t: Throwable) {
                Timber.tag("Net_err").d("Ошибка данных")
            }

        })
    }
}
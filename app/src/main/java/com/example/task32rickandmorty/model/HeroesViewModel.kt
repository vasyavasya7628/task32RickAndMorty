package com.example.task32rickandmorty.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task32rickandmorty.data.HeroesApi
import com.example.task32rickandmorty.data.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class HeroesViewModel : ViewModel() {
    private val api: HeroesApi = HeroesApi.create()
    private var _heroes: MutableLiveData<List<HeroesUi>> =
        MutableLiveData<List<HeroesUi>>()
    val heroesExport: LiveData<List<HeroesUi>> get() = _heroes
    private var currentPage = 1
    private val MIN_PAGES = 1
    private val MAX_PAGES = 41
    private val PAGES_LIMIT = 42
    init {
        getDataNW()
    }

    fun loadNextPage() {
        getDataNW()
    }

    private fun getDataNW() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCharacter(currentPage).toDomain()
                val heroesUi: MutableList<HeroesUi> =
                    response.heroesList.map {
                        HeroesUi.Data(it)
                    }.toMutableList()
                when {
                    currentPage == MIN_PAGES -> {
                        heroesUi.add(HeroesUi.NextPage)
                        withContext(Dispatchers.Main) {
                            _heroes.value = heroesUi
                        }
                        currentPage++
                    }
                    currentPage < MAX_PAGES -> {
                        heroesUi.add(HeroesUi.NextPage)
                        withContext(Dispatchers.Main) {
                            _heroes.value = heroesUi
                        }
                        currentPage++
                    }
                    currentPage == PAGES_LIMIT ->{
                        Timber.d("Последняя страница")
                    }
                }

            } catch (e: Exception) {
                Timber.tag("Что-то не так с запросом")
            }


        }

    }
}



package com.example.task32rickandmorty.data

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://rickandmortyapi.com/api/"

interface HeroesApi {
    @GET("character")
    fun getCharacter(
        @Query("page")
        page: Int
    ): Call<HeroesNetwork>

    companion object {
        fun create(): HeroesApi {
            GlobalScope.launch {}
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(HeroesApi::class.java)

        }
    }
}
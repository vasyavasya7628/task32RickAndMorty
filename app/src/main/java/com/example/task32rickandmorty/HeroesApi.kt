package com.example.task32rickandmorty

import com.example.task32rickandmorty.data.HeroesNetwork
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://rickandmortyapi.com/api/character"
interface HeroesApi {
    @GET("page")
    fun heroesPage(
        @Query("id")
        page: Int
    ): Call<HeroesNetwork>

    companion object {
        fun create(): HeroesApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(HeroesApi::class.java)
        }
    }
}
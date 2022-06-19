package com.example.task32rickandmorty.model

import com.example.task32rickandmorty.data.HeroesLocal

sealed class HeroesUi{
    data class Data(
        val item: HeroesLocal
    ): HeroesUi()

    object NextPage: HeroesUi()
}
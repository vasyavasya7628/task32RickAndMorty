package com.example.task32rickandmorty.data

import com.example.task32rickandmorty.ui.HeroesUI

fun Result.toUI(): HeroesUI {
    return HeroesUI(
        image = image,
        species = type,
        gender = gender,
        name = name,
        episode = episode
    )
}
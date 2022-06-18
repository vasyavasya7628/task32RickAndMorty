package com.example.task32rickandmorty.data

fun Result.toDomain(): HeroesLocal {
    return HeroesLocal(
        image = image,
        species = type,
        gender = gender,
        name = name,
        episode = episode
    )
}
package com.example.task32rickandmorty.data
import com.example.task32rickandmorty.data.HeroesNetwork
import com.example.task32rickandmorty.data.HeroesLocal

fun Result.toDomain():HeroesLocal{
    return HeroesLocal(
        image = image,
        species = type,
        gender = gender,
        episode = episode
    )
}
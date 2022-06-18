package com.example.task32rickandmorty.data

import com.google.gson.annotations.SerializedName

data class HeroesLocal(
    val species: String,
    val gender: String,
    val image: String,
    val name: String,
    val episode: List<String>
)




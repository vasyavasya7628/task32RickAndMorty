package com.example.task32rickandmorty.data

data class PagesLocal(
    val next: String,
    val pages: Int,
    val heroesList: List<HeroesLocal>
)
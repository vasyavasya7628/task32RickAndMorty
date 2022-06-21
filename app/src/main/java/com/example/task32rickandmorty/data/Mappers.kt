package com.example.task32rickandmorty.data

fun HeroesNW.toDomain(): PagesLocal{
    return  PagesLocal(
        next = pagesNetwork.next,
        pages = pagesNetwork.count,
        heroesList = results.toDomain()
    )
}

private fun List<Result>.toDomain(): List<HeroesLocal> = map{
    HeroesLocal(
        name = it.name,
        species = it.species,
        gender = it.gender,
        image = it.image,
        episode = it.episode
    )
}



//fun Result.toDomain(): HeroesLocal {
//    return HeroesLocal(
//        image = image,
//        species = type,
//        gender = gender,
//        name = name,
//        episode = episode
//    )
//}
//
//fun PagesNetwork.toDomain(): PagesLocal{
//    return PagesLocal(
//        next = next,
//        pages = pages,
//        heroesList =
//    )
//}
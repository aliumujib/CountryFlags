package com.aliumujib.countryflags

import com.aliumujib.countryflags.presentation.models.*
import konveyor.base.randomBuild

object PresentationDataFactory {

    fun makeFilm(): FilmModel {
        return randomBuild()
    }

    fun makeSpecie(): SpecieModel {
        return randomBuild()
    }

    fun makePlanet(): PlanetModel {
        return randomBuild()
    }

    fun makeCharacterModel(): StarWarsCharacterModel {
        return randomBuild()
    }

    fun makeCharacter(): StarWarsCharacter {
        return randomBuild()
    }

    fun makeIntergerList(count: Int): List<Int> {
        val categories = mutableListOf<Int>()
        repeat(count) {
            categories.add(randomBuild())
        }
        return categories
    }

    fun makeFilmModelList(count: Int): List<FilmModel> {
        val categories = mutableListOf<FilmModel>()
        repeat(count) {
            categories.add(makeFilm())
        }
        return categories
    }

    fun makeCharacterModelList(count: Int): List<StarWarsCharacterModel> {
        val articles = mutableListOf<StarWarsCharacterModel>()
        repeat(count) {
            articles.add(makeCharacterModel())
        }
        return articles
    }

    fun makeDetailedCharacterModel(): DetailedCharacterModel {
        return randomBuild()
    }

    fun makeDetailedCharacter(): DetailedCharacter {
        return randomBuild()
    }

    fun makeFilmList(count: Int): List<Film> {
        val categories = mutableListOf<Film>()
        repeat(count) {
            categories.add(randomBuild())
        }
        return categories
    }

    fun makeCharacterList(count: Int): List<StarWarsCharacter> {
        val articles = mutableListOf<StarWarsCharacter>()
        repeat(count) {
            articles.add(randomBuild())
        }
        return articles
    }

}
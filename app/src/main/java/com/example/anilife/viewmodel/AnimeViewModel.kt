package com.example.anilife.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.anilife.repository.AnimeRepository
import com.example.anilife.util.Resource
import com.example.anilife.vo.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(animeRepository: AnimeRepository) : ViewModel() {

    private val animeRepo = animeRepository


    fun winterAnime(year: Int) = animeRepo.getWinterAnime(year).asLiveData()
    fun springAnime(year: Int) = animeRepo.getSpringAnime(year).asLiveData()
    fun summerAnime(year: Int) = animeRepo.getSummerAnime(year).asLiveData()
    fun fallAnime(year: Int) = animeRepo.getFallAnime(year).asLiveData()


    private var id: Int = 1

    private var year: Int = 0

    fun setYear(year: Int) {
        this.year = year
    }

    fun setMalID(id: Int) {
        this.id = id
    }

    fun anime(id: Int) = animeRepo.getAnime(id).asLiveData()

}
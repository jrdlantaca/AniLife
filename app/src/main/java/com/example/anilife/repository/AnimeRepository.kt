package com.example.anilife.repository


import android.util.Log
import androidx.room.withTransaction
import com.example.anilife.api.JikanService
import com.example.anilife.db.AnimeDatabase
import com.example.anilife.util.networkBoundResource
import com.example.anilife.vo.AiringAnime
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val db: AnimeDatabase,
    private val jikanService: JikanService
) {
    private val animeDao = db.animeDao()
    private var animeList = mutableListOf<AiringAnime>()

    fun getAnime(id: Int) = networkBoundResource(
        query = {
            animeDao.getAnime()
        },
        fetch = {
            delay(4000)
            val response = jikanService.getAnime(id)
            response.body()!!
        },
        saveFetchResult = { anime ->
            db.withTransaction {
                animeDao.deleteAnime()
                animeDao.insertAnime(anime)
            }
        }
    )

    fun getWinterAnime(year: Int) = networkBoundResource(

        query = {
            animeDao.getWinterAnime()
        },
        fetch = {
            delay(4000)
            var response = jikanService.getCurrentSeasonalAnime(year, "winter")
            if (response.body()!!.season_name == null) {
                delay(4000)
                response = jikanService.getCurrentSeasonalAnime(year - 1, "winter")
                response.body()!!.airingAnimeList.forEachIndexed { index, anime ->
                    animeList.add(index, anime!!)
                    animeList[index].season = "winter"
                    animeList[index].year = year
                }
            } else {
                response.body()!!.airingAnimeList.forEachIndexed { index, anime ->
                    animeList.add(index, anime!!)
                    animeList[index].season = "winter"
                    animeList[index].year = year
                }
            }
            animeList
        },
        saveFetchResult = { anime ->
            db.withTransaction {
                animeDao.deleteWinterAnime()
                animeDao.insertAiringAnime(anime)
                animeDao.deleteRatedRandKids()
            }
        }
    )

    fun getSpringAnime(year: Int) = networkBoundResource(
        query = {
            animeDao.getSpringAnime()
        },
        fetch = {
            delay(4000)
            var response = jikanService.getCurrentSeasonalAnime(year, "spring")

            if (response.body()!!.season_name == null) {
                delay(4000)
                response = jikanService.getCurrentSeasonalAnime(year - 1, "spring")
                response.body()!!.airingAnimeList.forEachIndexed { index, anime ->
                    animeList.add(index, anime!!)
                    animeList[index].season = "spring"
                    animeList[index].year = year
                }
            } else {
                response.body()!!.airingAnimeList.forEachIndexed { index, anime ->
                    animeList.add(index, anime!!)
                    animeList[index].season = "spring"
                    animeList[index].year = year
                }
            }
            animeList
        },
        saveFetchResult = { anime ->
            db.withTransaction {
                animeDao.deleteSpringAnime()
                animeDao.insertAiringAnime(anime)
                animeDao.deleteRatedRandKids()
            }
        }
    )

    fun getSummerAnime(year: Int) = networkBoundResource(
        query = {
            animeDao.getSummerAnime()
        },
        fetch = {
            delay(4000)
            var response = jikanService.getCurrentSeasonalAnime(year, "summer")
            if (response.body()!!.season_name == null) {
                delay(4000)
                response = jikanService.getCurrentSeasonalAnime(year - 1, "summer")
                response.body()!!.airingAnimeList.forEachIndexed { index, anime ->
                    animeList.add(index, anime!!)
                    animeList[index].season = "summer"
                    animeList[index].year = year
                }
            } else {
                response.body()!!.airingAnimeList.forEachIndexed { index, anime ->
                    animeList.add(index, anime!!)
                    animeList[index].season = "summer"
                    animeList[index].year = year
                }
            }
            animeList
        },
        saveFetchResult = { anime ->
            db.withTransaction {
                animeDao.deleteSummerAnime()
                animeDao.insertAiringAnime(anime)
                animeDao.deleteRatedRandKids()
            }
        }
    )

    fun getFallAnime(year: Int) = networkBoundResource(
        query = {
            animeDao.getFallAnime()
        },
        fetch = {
            delay(4000)
            var response = jikanService.getCurrentSeasonalAnime(year, "fall")
            if (response.body()!!.season_name == null) {
                delay(4000)
                response = jikanService.getCurrentSeasonalAnime(year - 1, "fall")
                response.body()!!.airingAnimeList.forEachIndexed { index, anime ->
                    animeList.add(index, anime!!)
                    animeList[index].season = "fall"
                    animeList[index].year = year
                }
            } else {
                response.body()!!.airingAnimeList.forEachIndexed { index, anime ->
                    animeList.add(index, anime!!)
                    animeList[index].season = "fall"
                    animeList[index].year = year
                }
            }
            animeList
        },
        saveFetchResult = { anime ->
            db.withTransaction {
                animeDao.deleteFallAnime()
                animeDao.insertAiringAnime(anime)
                animeDao.deleteRatedRandKids()
            }
        }
    )
}
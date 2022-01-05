package com.example.anilife.api

import com.example.anilife.vo.Anime
import com.example.anilife.vo.Season
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JikanService {
    @GET("season/{year}/{season}")
    suspend fun getCurrentSeasonalAnime(
        @Path("year") year: Int,
        @Path("season") season: String
    ) : Response<Season>

    @GET("anime/{id}")
    suspend fun getAnime(
        @Path("id") id : Int
    ) : Response<Anime>

}
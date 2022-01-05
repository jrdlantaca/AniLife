package com.example.anilife.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.anilife.vo.AiringAnime
import com.example.anilife.vo.Anime
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAiringAnime(airingAnimes: List<AiringAnime>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnime(anime: Anime)

    @Query("SELECT * FROM anime_table")
    fun getAnime() : Flow<Anime>

    @Query("DELETE FROM airing_anime_table WHERE rated_r=1 OR kids=1")
    fun deleteRatedRandKids()

    @Query("SELECT * FROM airing_anime_table WHERE season='winter'")
    fun getWinterAnime(): Flow<List<AiringAnime>>

    @Query("SELECT * FROM airing_anime_table WHERE season='spring'")
    fun getSpringAnime(): Flow<List<AiringAnime>>

    @Query("SELECT * FROM airing_anime_table WHERE season='summer'")
    fun getSummerAnime(): Flow<List<AiringAnime>>

    @Query("SELECT * FROM airing_anime_table WHERE season = 'fall'")
    fun getFallAnime(): Flow<List<AiringAnime>>

    @Query("DELETE FROM airing_anime_table WHERE season = 'winter'")
    fun deleteWinterAnime()

    @Query("DELETE FROM airing_anime_table WHERE season = 'spring'")
    fun deleteSpringAnime()

    @Query("DELETE FROM airing_anime_table WHERE season = 'summer'")
    fun deleteSummerAnime()

    @Query("DELETE FROM airing_anime_table WHERE season = 'fall'")
    fun deleteFallAnime()

    @Query("DELETE FROM airing_anime_table")
    fun deleteAiringAnime()

    @Query("DELETE FROM anime_table")
    fun deleteAnime()

}
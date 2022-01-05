package com.example.anilife.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Season(
    @Json(name="season_name")
    val season_name : String? = null,
    @Json(name="season_year")
    val season_year : Int? = null,
    @Json(name = "anime")
    val airingAnimeList: List<AiringAnime?> = emptyList()
)


@Entity(tableName = "airing_anime_table")
@TypeConverters(GenreConverters::class)
@JsonClass(generateAdapter = true)
data class AiringAnime(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @Json(name="mal_id")
    val mal_id : Int? = null,
    @Json(name = "image_url")
    val image_url: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name="airing_start")
    val airing_start : String? = null,
    @Json(name = "episodes")
    val episodes: Int? = null,
    @Json(name = "synopsis")
    val synopsis: String? = null,
    @Json(name = "type")
    val type : String? = null,
    @Json(name = "genres")
    val genres: List<Genre?>? = null,
    @Json(name = "r18")
    val rated_r : Boolean? = null,
    @Json(name = "kids")
    val kids : Boolean? = null,
    var season : String? = null,
    var year : Int? = null
)

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "name")
    var name: String? = null
)

class GenreConverters {
    @TypeConverter
    fun genresToString(list: List<Genre>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.toJson(list, type).toString()
    }

    @TypeConverter
    fun stringToGenres(json: String): List<Genre> {
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(json,type)
    }
}
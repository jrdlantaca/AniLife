package com.example.anilife.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "anime_table")
@TypeConverters(AnimeConverter::class)
@JsonClass(generateAdapter = true)
data class Anime(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Json(name = "mal_id")
    val mal_id: Int? = null,
    @Json(name = "image_url")
    val image_url: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "source")
    val source: String? = null,
    @Json(name = "episodes")
    val episodes: Int? = null,
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "synopsis")
    val synopsis: String? = null,
    @Json(name = "premiered")
    val premiered : String? = null,
    @Json(name = "genres")
    val genres: List<Genre?>? = null,
    @Json(name = "studios")
    val studio : List<Studio?>? = null,
    @Json(name = "rank")
    val rank: Int? = null,
    @Json(name = "popularity")
    val popularity: Int? = null,
    @Json(name = "favorites")
    val favorites: Int? = null,
    @Json(name = "aired")
    val aired : Aired? = null
)

@JsonClass(generateAdapter = true)
data class Aired(
    @Json(name = "string")
    val string : String? = null
)

@JsonClass(generateAdapter = true)
data class Studio(
    @Json(name = "name")
    val name : String? = null
)


class AnimeConverter{
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
    @TypeConverter
    fun studioToString(list: List<Studio>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Studio>>() {}.type
        return gson.toJson(list, type).toString()
    }

    @TypeConverter
    fun stringToStudio(json: String): List<Studio> {
        val gson = Gson()
        val type = object : TypeToken<List<Studio>>() {}.type
        return gson.fromJson(json,type)
    }

    @TypeConverter
    fun airedToString(aired: Aired) : String {
        val gson = Gson()
        val type = object : TypeToken<Aired>() {}.type
        return gson.toJson(aired, type).toString()
    }

    @TypeConverter
    fun stringToAired(json: String) : Aired {
        val gson = Gson()
        val type = object : TypeToken<Aired>() {}.type
        return gson.fromJson(json, type)
    }
}
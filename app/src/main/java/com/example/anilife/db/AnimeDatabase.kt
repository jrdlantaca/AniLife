package com.example.anilife.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.anilife.vo.AiringAnime
import com.example.anilife.vo.Anime

@Database(entities = [AiringAnime::class, Anime::class], version = 1, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}
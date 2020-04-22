package com.icar05.githubsearch.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.icar05.githubsearch.data.model.GitHabInfoEntity


@Database(
    entities = [GitHabInfoEntity::class],
    version = 1,
    exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun itemDao(): GitHubDao
}
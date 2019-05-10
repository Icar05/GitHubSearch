package com.Icar05.githubsearch.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.Icar05.githubsearch.data.database.ItemDao
import com.Icar05.githubsearch.domain.models.Item

@Database(
        entities = [Item::class],
        version = 1,
        exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}
package com.Icar05.githubsearch.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.Icar05.githubsearch.domain.models.Item


@Dao
interface ItemDao {

    @Query("SELECT * FROM item WHERE name LIKE  :name OR full_name LIKE :name OR description LIKE :name ORDER BY forks desc  ")
    fun getRepos(name:String): List<Item>

    @Query("SELECT * FROM item ")
    fun getRepos(): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items:  List<Item>)

    @Query("DELETE FROM item")
    fun deleteAll()

}
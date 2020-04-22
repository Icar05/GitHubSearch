package com.icar05.githubsearch.data.storage


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.icar05.githubsearch.data.model.GitHabInfoEntity


@Dao
interface GitHubDao {

    @Query("SELECT * FROM githabinfoentity WHERE name LIKE  :name OR full_name LIKE :name OR description LIKE :name ORDER BY forks desc  ")
    fun getRepos(name:String): List<GitHabInfoEntity>

    @Query("SELECT * FROM githabinfoentity ")
    fun getRepos(): List<GitHabInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items:  List<GitHabInfoEntity>)

    @Query("DELETE FROM githabinfoentity")
    fun deleteAll()

}
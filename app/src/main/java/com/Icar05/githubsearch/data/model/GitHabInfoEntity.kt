package com.Icar05.githubsearch.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class GitHabInfoEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val full_name: String?,
    val description: String?,
    val url: String?,
    val language: String?,
    val forks: Int
): Parcelable
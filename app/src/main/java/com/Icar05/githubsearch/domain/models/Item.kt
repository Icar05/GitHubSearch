package com.Icar05.githubsearch.domain.models

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class Item (
        @PrimaryKey()
        val id: Int,
        val name: String,
        val full_name: String?,
        val description: String?,
        val url: String?,
        val language: String?,
        val forks: Int
): Parcelable


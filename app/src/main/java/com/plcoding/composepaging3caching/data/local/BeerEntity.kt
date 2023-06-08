package com.plcoding.composepaging3caching.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BeerTable")
data class BeerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String?,
)

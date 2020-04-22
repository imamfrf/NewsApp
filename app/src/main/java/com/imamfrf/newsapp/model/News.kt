package com.imamfrf.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var title: String,
    var publisher: String,
    var image: String
) {
}